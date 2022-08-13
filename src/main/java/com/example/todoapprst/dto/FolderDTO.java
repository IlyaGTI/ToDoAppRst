package com.example.todoapprst.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FolderDTO {
    private String nameFolder;
    private String userName;
    private Set<String> foldersInner;
    private Set<String> notesName;
}
