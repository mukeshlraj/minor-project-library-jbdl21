package com.gfg.jbdl.library.repositories;

import com.gfg.jbdl.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
