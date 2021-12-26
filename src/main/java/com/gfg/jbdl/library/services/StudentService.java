package com.gfg.jbdl.library.services;

import com.gfg.jbdl.library.models.Book;
import com.gfg.jbdl.library.models.Student;
import com.gfg.jbdl.library.models.User;
import com.gfg.jbdl.library.repositories.StudentRepository;
import com.gfg.jbdl.library.repositories.UserRepository;
import com.gfg.jbdl.library.requests.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${app.security.student_role}")
    String STUDENT_ROLE;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void addStudent(StudentCreateRequest studentCreateRequest) {
        Student student = studentCreateRequest.toStudent();
        student =  studentRepository.save(student);

        User user = User.builder()
                .username(studentCreateRequest.getEmail())
                .password(passwordEncoder.encode(studentCreateRequest.getPassword()))
                .authorities(STUDENT_ROLE)
                .isStudent(true)
                .createdOn(student.getRegisteredOn())
                .build();

        userRepository.save(user);
    }

    public Student getStudent(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void addOrUpdateBook(Student student) {
        studentRepository.save(student);
    }
}
