package com.gfg.jbdl.library.repositories;

import com.gfg.jbdl.library.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
