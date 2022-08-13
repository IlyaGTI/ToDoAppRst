package com.example.todoapprst.service.impl;

import com.example.todoapprst.entity.Folder;
import com.example.todoapprst.entity.User;
import com.example.todoapprst.repository.FolderRepository;
import com.example.todoapprst.repository.UserRepository;
import com.example.todoapprst.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FolderRepository folderRepository;

    @Override
    public User createUser(User user) {

        Folder folder = new Folder();
        folder.setNameFolder("root");
        folder.setUser(user);
        folderRepository.save(folder);

        Set<Folder> defaultFolder = new HashSet<>();
        defaultFolder.add(folder);

        user.setFolders(defaultFolder);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        String userName = principal.getName();
        User user = userRepository.findUserByUserName(userName);
        return user;
    }

    @Override
    public User getByUserName(String userName) {
        User user = userRepository.findUserByUserName(userName);
        return user;
    }
}
