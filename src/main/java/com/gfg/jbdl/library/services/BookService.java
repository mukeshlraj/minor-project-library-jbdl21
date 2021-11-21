package com.gfg.jbdl.library.services;

import com.gfg.jbdl.library.models.Author;
import com.gfg.jbdl.library.models.Book;
import com.gfg.jbdl.library.repositories.BookRepository;
import com.gfg.jbdl.library.requests.BookCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookService {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository bookRepository;

    @Transactional
    public void addBook(BookCreateRequest bookCreateRequest){
        Book book = bookCreateRequest.toBook();
        Author author = authorService.getAuthorByEmail(bookCreateRequest.getEmail());

        if (author == null) {
            author = Author.builder()
                    .name(bookCreateRequest.getName())
                    .country(bookCreateRequest.getCountry())
                    .email(bookCreateRequest.getEmail())
                    .build();

            author = authorService.addAuthor(author);
        }

        book.setAuthor(author);

        bookRepository.save(book);
    }

    public Book getBookByID(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void updateBook(int id, int cost) {
//        Book book = bookRepository.findById(id).orElse(null);
//        book.setCost(cost);
//        bookRepository.save(book);
        bookRepository.updateBook(id, cost);
    }

    public void addOrUpdateBook(Book book) {
        bookRepository.save(book);
    }
}
