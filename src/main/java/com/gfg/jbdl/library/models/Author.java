package com.gfg.jbdl.library.models;

import com.sun.javafx.geom.transform.Identity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    @OneToMany
    private List<Book> books;// column will not be created
    private String country;
}
