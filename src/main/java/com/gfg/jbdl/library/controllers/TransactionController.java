package com.gfg.jbdl.library.controllers;

import com.gfg.jbdl.library.models.User;
import com.gfg.jbdl.library.services.TrasactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TrasactionService trasactionService;

    @PostMapping("/transaction/issue_book")
    public String issueBook(@RequestParam("book_id") int bookId) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        int studentId = user.getId();

        return trasactionService.issueBook(bookId, studentId);
    }

    //student
    @GetMapping("/transaction/fine")
    public Integer getFine(@RequestParam("book_id") int bookId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        int studentId = user.getId();

        return trasactionService.getFine(bookId, studentId);
    }

    //admin
    @GetMapping("/transaction/fine/student_id/")
    public Integer getFineByStudentId(@RequestParam("bookID") int bookID, @RequestParam("studentID") int studentID) {
        return trasactionService.getFine(bookID, studentID);
    }

    @PostMapping("/transaction/return_book")
    public void returnBook(@RequestParam("book_id") int bookId, @RequestParam("student_id") int studentId , @RequestParam("fine") int fine) {
        trasactionService.returnBook(bookId, studentId, fine);
    }
}
