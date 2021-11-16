package com.gfg.jbdl.library.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class TransactionController {

    @PostMapping("/issue")
    public void issueBook(@RequestParam("bookID") int bookID, @RequestParam("studentID") int studentID) {

    }

    @GetMapping("/fine")
    public Integer getFine(@RequestParam("bookID") int bookID, @RequestParam("studentID") int studentID) {
        return null;
    }

    @PostMapping("/return")
    public void returnBook() {

    }
}
