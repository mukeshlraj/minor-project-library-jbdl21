package com.gfg.jbdl.library.controllers;

import com.gfg.jbdl.library.models.Student;
import com.gfg.jbdl.library.requests.StudentCreateRequest;
import com.gfg.jbdl.library.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void addStudent(@RequestBody StudentCreateRequest studentCreateRequest) {
        studentService.addStudent(studentCreateRequest);
    }

    @GetMapping("/student")
    public Student getStudent(@RequestParam("id") int id) {
        return studentService.getStudent(id);
    }
}
