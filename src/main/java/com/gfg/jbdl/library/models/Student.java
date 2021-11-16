package com.gfg.jbdl.library.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String country;
    private int age;

    @Column(unique = true, nullable = false)
    private String contact;

    @CreationTimestamp
    private Date registeredOn;

    @OneToMany
    private List<Book> books;

    @OneToMany
    private List<Transactions> transactions;
    private int totalFine;
}
