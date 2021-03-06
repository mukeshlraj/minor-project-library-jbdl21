package com.gfg.jbdl.library.controllers;

import com.gfg.jbdl.library.models.Book;
import com.gfg.jbdl.library.models.User;
import com.gfg.jbdl.library.requests.BookCreateRequest;
import com.gfg.jbdl.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void createBook(@RequestBody BookCreateRequest bookCreateRequest){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if (user.isStudent())
            throw new AuthorizationServiceException("Students cannot create a book");

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
