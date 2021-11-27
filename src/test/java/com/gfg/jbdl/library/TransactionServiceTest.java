package com.gfg.jbdl.library;

import com.gfg.jbdl.library.models.*;
import com.gfg.jbdl.library.repositories.TransactionRepository;
import com.gfg.jbdl.library.services.BookService;
import com.gfg.jbdl.library.services.StudentService;
import com.gfg.jbdl.library.services.TrasactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    TrasactionService trasactionService;

    @Mock
    StudentService studentService;

    @Mock
    BookService bookService;

    @Mock
    TransactionRepository transactionRepository;

    @Test
    public void testIssueBook() throws Exception {
        this.trasactionService = new TrasactionService(bookService, studentService, transactionRepository, 3,14,1);

        Book book = Book.builder()
                .id(1)
                .name("ABC")
                .cost(100)
                .isAvailable(true)
                .student(null)
                .build();

        Student student = Student.builder()
                .id(3)
                .books(new ArrayList<>())
                .build();

        Transactions transactions = Transactions.builder()
                .id(1)
                .transactionId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .transactionStatus(TransactionStatus.PENDING)
                .student(student)
                .book(book)
                .build();

        when(bookService.getBookByID(1)).thenReturn(book);
        when(studentService.getStudent(3)).thenReturn(student);
        when(transactionRepository.save(any())).thenReturn(transactions);

        String transactionId = trasactionService.issueBook(1, 3);

        assertEquals(transactions.getTransactionId(), transactionId);

        verify(transactionRepository, times(2)).save(any());
    }
}
