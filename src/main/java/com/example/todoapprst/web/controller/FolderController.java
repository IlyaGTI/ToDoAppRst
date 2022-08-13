package com.example.todoapprst.web.controller;

import com.example.todoapprst.dto.FolderDTO;
import com.example.todoapprst.entity.Folder;
import com.example.todoapprst.service.FolderService;
import com.example.todoapprst.service.impl.FolderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/folder")
@RequiredArgsConstructor
public class FolderController {

    private final FolderServiceImpl folderService;

    @PostMapping(path = "/new")
    public ResponseEntity<FolderDTO> createFolderInRoot(@RequestBody FolderDTO folder, Principal principal) {

        folderService.createFolderInRoot(folder, principal);
        return new ResponseEntity<>(folder, HttpStatus.CREATED);
    }

    @PostMapping(path = "/new/{folder}")
    public ResponseEntity<FolderDTO> createFolder(@PathVariable (name = "folder") String folderIn,
                                                  @RequestBody FolderDTO folder,
                                                  Principal principal) {

        folderService.createFolder(folderIn ,folder, principal);
        return new ResponseEntity<>(folder, HttpStatus.CREATED);
    }


    @GetMapping(path = "/all")
    public ResponseEntity<Set<FolderDTO>> allUsersFolder(Principal principal) {
        Set<FolderDTO> usersFolders = folderService.getAllUsersFolders(principal);
        return new ResponseEntity<>(usersFolders, HttpStatus.OK);
    }
    @GetMapping("/{folder}")
    public ResponseEntity<FolderDTO> getOneFolder(@PathVariable(name = "folder") String name){
        FolderDTO folderDTO = folderService.findFolder(name);
        return new ResponseEntity<>(folderDTO, HttpStatus.OK);
    }
}
