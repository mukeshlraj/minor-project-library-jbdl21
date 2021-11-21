package com.gfg.jbdl.library.services;

import com.gfg.jbdl.library.models.Book;
import com.gfg.jbdl.library.models.Student;
import com.gfg.jbdl.library.repositories.StudentRepository;
import com.gfg.jbdl.library.requests.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void addStudent(StudentCreateRequest studentCreateRequest) {
        Student student = studentCreateRequest.toStudent();
        studentRepository.save(student);
    }

    public Student getStudent(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void addOrUpdateBook(Student student) {
        studentRepository.save(student);
    }
}
