package com.example.todoapprst.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter

public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_folder", length = 25)
    private String nameFolder;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "folder_id")
    private User user;
    @ElementCollection
    private Set<String> nameInner;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "folder")
    private Set<Note> note;
}
