package com.example.todoapprst.service;

import com.example.todoapprst.entity.User;

import java.security.Principal;

public interface UserService {

    User createUser(User user);

    User getUserByPrincipal(Principal principal);

    User getByUserName(String userName);

}
