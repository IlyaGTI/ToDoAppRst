package com.example.todoapprst.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "note_name", length = 35)
    private String nameNot;
    @Column(name = "text_note", length = 355)
    private String textNote;
    @Column(name = "finish_note")
    private Boolean finished;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "folder_id")
    private Folder folder;
    @JsonFormat(pattern = "yyyy-HH-dd HH:mm")
    private LocalDateTime date;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note = (Note) o;
        return id.equals(note.id) && finished.equals(note.finished);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, finished);
    }
}
