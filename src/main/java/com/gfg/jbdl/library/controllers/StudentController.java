package com.gfg.jbdl.library.controllers;

import com.gfg.jbdl.library.models.Student;
import com.gfg.jbdl.library.models.User;
import com.gfg.jbdl.library.requests.StudentCreateRequest;
import com.gfg.jbdl.library.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void addStudent(@RequestBody StudentCreateRequest studentCreateRequest) {
        studentService.addStudent(studentCreateRequest);
    }

    //student
    @GetMapping("/student")
    public Student getStudent() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        int studentId = user.getId();
        return studentService.getStudent(studentId);
    }

    //admin
    @GetMapping("/student/id")
    public Student getStudentById(@RequestParam("id") int id) {
        return studentService.getStudent(id);
    }
}
