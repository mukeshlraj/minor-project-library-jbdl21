package com.gfg.jbdl.library.repositories;

import com.gfg.jbdl.library.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("from Author where email= :email")
    Author findByEmail(String email);
}
