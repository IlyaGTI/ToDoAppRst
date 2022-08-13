package com.example.todoapprst.repository;

import com.example.todoapprst.entity.Folder;
import com.example.todoapprst.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select n from Note as n where n.nameNot=:name")
    Note findNoteByNameNot(@Param("name") String nameNot);

    Set<Note> findAllByFolder(Folder folder);
}
