package com.gfg.jbdl.library.controllers;

import com.gfg.jbdl.library.services.TrasactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TrasactionService trasactionService;

    @PostMapping("/issue_book")
    public String issueBook(@RequestParam("book_id") int bookId, @RequestParam("student_id") int studentId) throws Exception {
        return trasactionService.issueBook(bookId, studentId);
    }

    @GetMapping("/fine")
    public Integer getFine(@RequestParam("bookID") int bookID, @RequestParam("studentID") int studentID) {
        return trasactionService.getFine(bookID, studentID);
    }

    @PostMapping("/return_book")
    public void returnBook(@RequestParam("book_id") int bookId, @RequestParam("student_id") int studentId , @RequestParam("fine") int fine) {
        trasactionService.returnBook(bookId, studentId, fine);
    }
}
