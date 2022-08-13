package com.example.todoapprst.dto;

import com.example.todoapprst.entity.Note;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {
    private String nameNote;
    private String userName;
    private String text;
    private String folderName;
    private Boolean finished;

}
