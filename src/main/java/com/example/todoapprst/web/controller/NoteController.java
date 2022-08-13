package com.example.todoapprst.web.controller;

import com.example.todoapprst.dto.NoteDTO;
import com.example.todoapprst.entity.Note;
import com.example.todoapprst.service.NoteService;
import com.example.todoapprst.web.payload.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping(path = "{nameFolder}/create")
    public ResponseEntity<NoteDTO> creatingNote(@RequestBody NoteDTO noteDTO, Principal principal,
                                                @PathVariable(name = "nameFolder") String nameFolder) {
        NoteDTO createdNoteDTO = noteService.createNote(noteDTO, principal, nameFolder);
        return new ResponseEntity<>(createdNoteDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/finish/{noteName}")
    public ResponseEntity<Message> finishedNote(@PathVariable(name = "noteName") String nameNote) {
        noteService.finishedNote(nameNote);
        return new ResponseEntity<>(new Message("Note was completed!"), HttpStatus.OK);
    }

    @GetMapping(path = "/{noteName}")
    public ResponseEntity<NoteDTO> getNote(@PathVariable(name = "noteName") String nameNote) {
        NoteDTO noteDTO = noteService.getNoteDTOByName(nameNote);
        return new ResponseEntity<>(noteDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{noteName}")
    public ResponseEntity<Message> deleteNote(@PathVariable(name = "noteName") String nameNote) {
        noteService.deleteNoteByName(nameNote);
        return new ResponseEntity<>(new Message("Note was deleted"), HttpStatus.OK);
    }

    @GetMapping(path = "/all/{folderName}/**")
    public ResponseEntity<Set<NoteDTO>> getAllNotesByFolder(@PathVariable(name = "folderName") String nameFolder) {
        Set<NoteDTO> noteDTOS = noteService.getAllNotesByFolder(nameFolder);
        return new ResponseEntity<>(noteDTOS, HttpStatus.OK);
    }

}
