package com.example.todoapprst.facade;

import com.example.todoapprst.dto.FolderDTO;
import com.example.todoapprst.dto.NoteDTO;
import com.example.todoapprst.entity.Folder;
import com.example.todoapprst.entity.Note;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FolderFacade {

    public FolderDTO FolderToFolderDTO(Folder folder) {

        FolderDTO folderDTO = new FolderDTO();
        folderDTO.setNameFolder(folder.getNameFolder());
        folderDTO.setUserName(folder.getUser().getUserName());
        folderDTO.setFoldersInner(folder.getNameInner());
        folderDTO.setNotesName(convertOneToManyRel(folder.getNote()));

        return folderDTO;
    }

    public Set<String> convertOneToManyRel(Set<Note> notes) {

        Set<String> namesNote = new HashSet<>();
        List<Note> note = notes.stream().collect(Collectors.toList());
        for (Note note1 : note) {
            namesNote.add(note1.getNameNot());
        }
        return namesNote;
    }

    public Set<FolderDTO> setFolderToSetFolderDTO(Set<Folder> folders){
        return folders.stream()
                .map(folder -> new FolderDTO(folder.getNameFolder(), folder.getUser().getUserName(),
                        folder.getNameInner(),
                        convertOneToManyRel(folder.getNote())))
                .collect(Collectors.toSet());
    }
}
