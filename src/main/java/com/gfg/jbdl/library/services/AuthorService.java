package com.gfg.jbdl.library.services;

import com.gfg.jbdl.library.models.Author;
import com.gfg.jbdl.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Author getAuthorByEmail(String email) {
        return authorRepository.findByEmail(email);
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }
}
