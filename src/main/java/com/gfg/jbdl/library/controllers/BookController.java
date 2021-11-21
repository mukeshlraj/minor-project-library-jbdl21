package com.gfg.jbdl.library.controllers;

import com.gfg.jbdl.library.models.Book;
import com.gfg.jbdl.library.requests.BookCreateRequest;
import com.gfg.jbdl.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void createBook(@RequestBody BookCreateRequest bookCreateRequest){
        bookService.addBook(bookCreateRequest);
    }

    @GetMapping("/book")
    public Book getBook(@RequestParam("id") int id) {
        return bookService.getBookByID(id);
    }

    @PutMapping("/book")
    public void updateBook(@RequestParam("id") int id, @RequestParam("cost") int cost) {
        bookService.updateBook(id, cost);
    }
}
