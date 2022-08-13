package com.example.todoapprst.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", length = 45, unique = true)
    private String userName;
    @Column(name = "password")
    private String password;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Folder> folders;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Note> notes;
}
