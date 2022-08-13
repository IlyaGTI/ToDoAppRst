package com.example.todoapprst.service;

import com.example.todoapprst.dto.NoteDTO;
import com.example.todoapprst.entity.Folder;
import com.example.todoapprst.entity.Note;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Set;

public interface NoteService {

    NoteDTO createNote(NoteDTO noteDTO, Principal principal, String name);

    Set<NoteDTO> getAllNotesByFolder(String folder);

    NoteDTO getNoteDTOByName(String nameNote);

    void deleteNoteByName(String nameNote);

    void finishedNote(String nameNote);


}
