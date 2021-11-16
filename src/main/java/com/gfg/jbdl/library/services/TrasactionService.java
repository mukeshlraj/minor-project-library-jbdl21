package com.gfg.jbdl.library.services;

import com.gfg.jbdl.library.models.Book;
import com.gfg.jbdl.library.models.Student;
import com.gfg.jbdl.library.models.TransactionStatus;
import com.gfg.jbdl.library.models.Transactions;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrasactionService {

    BookService bookService;
    StudentService studentService;

    public String issueBook(int bookId, int studentId) {
        Book book = bookService.getBookByID(bookId);

        if (book == null)
            return "Book is not found";

        Student student = studentService.getStudent(studentId);

        if (student == null)
            return "Student not found";

        Transactions transactions = Transactions.builder()
                .transactionId(UUID.randomUUID().toString())
                .transactionStatus(TransactionStatus.PENDING)
                .student(student)
                .book(book)
                .build();

        return transactions.getTransactionId();
    }
}
