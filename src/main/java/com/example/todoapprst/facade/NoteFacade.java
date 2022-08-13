package com.example.todoapprst.facade;

import com.example.todoapprst.dto.NoteDTO;
import com.example.todoapprst.entity.Note;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NoteFacade {

    public NoteDTO noteToNoteDTO(Note note) {

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setNameNote(note.getNameNot());
        noteDTO.setText(note.getTextNote());
        noteDTO.setFinished(note.getFinished());
        noteDTO.setUserName(note.getUser().getUserName());
        noteDTO.setFolderName(note.getFolder().getNameFolder());

        return noteDTO;
    }

    public Set<NoteDTO> setNoteToSetNoteDTO(Set<Note> notes){
        return notes.stream()
                .map(note -> new NoteDTO(note.getNameNot(), note.getUser().getUserName(),
                        note.getTextNote(),note.getFolder().getNameFolder(), note.getFinished()))
                .collect(Collectors.toSet());
    }
}
