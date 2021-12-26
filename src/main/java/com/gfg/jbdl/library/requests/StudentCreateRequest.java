package com.gfg.jbdl.library.requests;

import com.gfg.jbdl.library.models.Student;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCreateRequest {
    private String name;
    private int age;
    private String country;
    private String contact;
    private String email;

    private String password;

    public Student toStudent() {
        return Student.builder()
                .name(name)
                .age(age)
                .email(email)
                .password(password)
                .contact(contact)
                .country(country)
                .build();
    }
}
