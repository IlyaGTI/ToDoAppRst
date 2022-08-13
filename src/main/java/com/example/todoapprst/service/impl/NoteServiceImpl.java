package com.example.todoapprst.service.impl;

import com.example.todoapprst.dto.NoteDTO;
import com.example.todoapprst.entity.Folder;
import com.example.todoapprst.entity.Note;
import com.example.todoapprst.entity.User;
import com.example.todoapprst.facade.NoteFacade;
import com.example.todoapprst.repository.FolderRepository;
import com.example.todoapprst.repository.NoteRepository;
import com.example.todoapprst.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    @Autowired
    private final FolderRepository folderRepository;
    private final NoteRepository noteRepository;
    private final UserServiceImpl userService;
    private final NoteFacade noteFacade;

    @Override
    public NoteDTO createNote(NoteDTO noteDTO, Principal principal, String name) {
        Note note = new Note();
        User user = userService.getUserByPrincipal(principal);
        note.setNameNot(noteDTO.getNameNote());
        note.setTextNote(noteDTO.getText());
        note.setFolder(folderRepository.findFolderByNameFolder(name));
        note.setUser(user);
        note.setFinished(false);
        noteRepository.save(note);

        return noteFacade.noteToNoteDTO(note);
    }


    @Override
    public Set<NoteDTO> getAllNotesByFolder(String folder) {
        Folder folder1 = folderRepository.findFolderByNameFolder(folder);
        Set<Note> notes = noteRepository.findAllByFolder(folder1);
        notes.stream().sorted(Comparator.comparing(Note :: getFinished))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return noteFacade.setNoteToSetNoteDTO(notes);
    }

    @Override
    public NoteDTO getNoteDTOByName(String nameNote) {
        Note note = getNoteByName(nameNote);
        return noteFacade.noteToNoteDTO(note);
    }


    public Note getNoteByName(String nameNote) {
        Note note = noteRepository.findNoteByNameNot(nameNote);
        return note;
    }

    @Override
    public void deleteNoteByName(String nameNote) {
        Note note = getNoteByName(nameNote);
        noteRepository.delete(note);
    }

    @Override
    public void finishedNote(String nameNote) {
        Note note = getNoteByName(nameNote);
        note.setFinished(true);
        noteRepository.save(note);
    }
}
