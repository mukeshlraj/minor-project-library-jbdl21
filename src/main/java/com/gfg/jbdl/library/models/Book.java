package com.gfg.jbdl.library.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @JoinColumn
    @ManyToOne
    private Author author;

    @JoinColumn
    @ManyToOne
    private Student student;
    private String publisher;
    private int cost;
    private boolean isAvailable;

    @CreationTimestamp
    private Date addedOn;
}
