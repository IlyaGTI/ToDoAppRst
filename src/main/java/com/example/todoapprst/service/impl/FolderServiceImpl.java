package com.example.todoapprst.service.impl;

import com.example.todoapprst.dto.FolderDTO;
import com.example.todoapprst.entity.Folder;
import com.example.todoapprst.entity.User;
import com.example.todoapprst.facade.FolderFacade;
import com.example.todoapprst.repository.FolderRepository;
import com.example.todoapprst.service.FolderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
@AllArgsConstructor
public class FolderServiceImpl implements FolderService {

    @Autowired
    private final FolderRepository folderRepository;
    private final UserServiceImpl userService;
    private final FolderFacade folderFacade;

    public FolderDTO createFolderInRoot(FolderDTO folderDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        Folder rootFolder = folderRepository.findFolderByNameFolderAndUser("root", user);
        Folder folder = new Folder();
        rootFolder.getNameInner().add(folderDTO.getNameFolder());
        rootFolder.setNameInner(rootFolder.getNameInner());
        folder.setNameFolder(folderDTO.getNameFolder());
        folder.setUser(user);
        folderRepository.save(folder);
        folderRepository.save(rootFolder);
        return folderFacade.FolderToFolderDTO(folder);
    }


    public FolderDTO createFolder(String nameFolder,FolderDTO folderDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        Folder mainFolder = folderRepository.findFolderByNameFolderAndUser(nameFolder, user);
        Folder folder = new Folder();
        mainFolder.getNameInner().add(folderDTO.getNameFolder());
        mainFolder.setNameInner(mainFolder.getNameInner());
        folder.setNameFolder(folderDTO.getNameFolder());
        folder.setUser(user);
        folderRepository.save(folder);
        folderRepository.save(mainFolder);
        return folderFacade.FolderToFolderDTO(folder);
    }

    @Override
    public Set<FolderDTO> getAllUsersFolders(Principal principal) {
        User user = userService.getByUserName(principal.getName());
        return folderFacade.setFolderToSetFolderDTO(folderRepository.findAllByUser(user));
    }

    @Override
    public FolderDTO findFolder(String name) {
        Folder folder = folderRepository.findFolderByNameFolder(name);
        return folderFacade.FolderToFolderDTO(folder);
    }

}
