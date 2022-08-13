package com.example.todoapprst.service;

import com.example.todoapprst.dto.FolderDTO;
import com.example.todoapprst.entity.Folder;
import com.example.todoapprst.entity.User;

import java.security.Principal;
import java.util.Set;

public interface FolderService {

    FolderDTO createFolder(String folder, FolderDTO folderDTO, Principal principal);

    Set<FolderDTO> getAllUsersFolders(Principal principal);

    FolderDTO findFolder(String name);


}
