package com.gfg.jbdl.library.services;

import com.gfg.jbdl.library.models.*;
import com.gfg.jbdl.library.repositories.TransactionRepository;
import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TrasactionService {

    @Autowired
    BookService bookService;
    @Autowired
    StudentService studentService;
    @Autowired
    TransactionRepository transactionRepository;

    // get the book and the student
    // check the book availability
    // check the student threshold
    // create transaction, making the book unavailable + increment thd student threshold by 1
    int maxAllotment;
    int daysAllotted;
    int finePerDay;

    public TrasactionService(BookService bookService,
                              StudentService studentService,
                              TransactionRepository transactionRepository,
                              @Value("${books.max-allotment}") int maxAllotment,
                              @Value("${books.allotted-time}") int daysAllotted,
                              @Value("${books.fine-per-day}")int finePerDay) {
        this.bookService = bookService;
        this.studentService = studentService;
        this.transactionRepository = transactionRepository;
        this.maxAllotment = maxAllotment;
        this.daysAllotted = daysAllotted;
        this.finePerDay = finePerDay;
    }

    @Transactional
    public String issueBook(int bookId, int studentId) throws Exception {
        Book book = bookService.getBookByID(bookId);

        if (book == null || !book.isAvailable())
            return "Book is not found";

        Student student = studentService.getStudent(studentId);

        if (student == null)
            return "Student not found";

        Transactions transactions = Transactions.builder()
                .transactionId(UUID.randomUUID().toString())
                .transactionStatus(TransactionStatus.PENDING)
                .transactionType(TransactionType.ISSUE)
                .student(student)
                .book(book)
                .build();

        try {
            transactions = transactionRepository.save(transactions);
            book.setAvailable(false);
            book.setStudent(student);
            bookService.addOrUpdateBook(book);

            transactions.setTransactionStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(transactions);
        }
        catch (Exception e){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Transaction : " + transactions.getTransactionId() + " has failed");
        }

        return transactions.getTransactionId();
    }

    public Integer getFine(int bookID, int studentID) {
        // get the issue date from transaction table
        // calc fine : (returnDate - IssueDate) * finePerDay;

        List<Transactions> transactionsList = transactionRepository.getTransactions(studentID, bookID, TransactionStatus.SUCCESS);
        Transactions transactions = transactionsList.get(transactionsList.size() - 1);

        Date transactionDate = transactions.getTransactionTime();
        long timePassedInMillis = System.currentTimeMillis() - transactionDate.getTime();
        Long daysPassed = TimeUnit.DAYS.convert(timePassedInMillis, TimeUnit.MILLISECONDS);

        if (daysPassed > daysAllotted) {
            return (daysPassed.intValue() - daysAllotted) * finePerDay;
        }
        return 0;
    }

    @Transactional
    public String returnBook(int bookId, int studentId, int fine) {
        // if there is fine, then add it to the student totalFine
        // make the book available
        // mark in it the transaction db

        Book book = bookService.getBookByID(bookId);

        if (book == null || book.isAvailable()) {
            return "The book is not found or it is not issued to anyone";
        }

        Student student = studentService.getStudent(studentId);

        if (student == null || book.getStudent().getId() != studentId) {
            return "Student is not found or book is not issued to this student";
        }

        Transactions transactions = Transactions.builder()
                .transactionId(UUID.randomUUID().toString())
                .transactionType(TransactionType.RETURN)
                .transactionStatus(TransactionStatus.PENDING)
                .student(student)
                .book(book)
                .build();

        try {
            transactions = transactionRepository.save(transactions);
            book.setAvailable(true);
            book.setStudent(null);

            bookService.addOrUpdateBook(book);

            if (fine > 0) {
                student.setTotalFine(student.getTotalFine() + fine);
                studentService.addOrUpdateBook(student);
            }

            transactions.setTransactionStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(transactions);
        }
        catch (Exception e) {
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
        }
        return transactions.getTransactionId();
    }
}
