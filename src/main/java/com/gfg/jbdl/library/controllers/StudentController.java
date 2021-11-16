package com.gfg.jbdl.library.controllers;

import com.gfg.jbdl.library.models.Student;
import com.gfg.jbdl.library.requests.StudentCreateRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @PostMapping("/student")
    public void addStudent(@RequestBody StudentCreateRequest studentCreateRequest) {

    }

    @GetMapping("/student")
    public Student getStudent(@RequestParam("id") int id) {
        return null;
    }
}
