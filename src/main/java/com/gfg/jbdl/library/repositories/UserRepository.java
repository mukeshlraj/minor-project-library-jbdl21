package com.gfg.jbdl.library.repositories;

import com.gfg.jbdl.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String user);
}
