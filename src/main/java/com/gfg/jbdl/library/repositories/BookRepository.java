package com.gfg.jbdl.library.repositories;

import com.gfg.jbdl.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Modifying
    @Transactional
    @Query("update Book b set b.cost= :cost where b.id = :id")
    void updateBook(int id, int cost);
}
