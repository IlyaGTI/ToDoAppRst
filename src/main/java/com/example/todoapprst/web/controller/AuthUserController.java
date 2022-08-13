package com.example.todoapprst.web.controller;

import com.example.todoapprst.entity.User;
import com.example.todoapprst.service.impl.UserServiceImpl;
import com.example.todoapprst.web.payload.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@PreAuthorize("permitAll()")
public class AuthUserController {

    private final UserServiceImpl userService;

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        User user = (principal instanceof User) ? (User) principal : null;
        return Objects.nonNull(user) ? this.userService.getByUserName(user.getUserName()) : null;
    }

    @PostMapping(path = "/regist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> registration(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>(new Message("Пользователь создан"), HttpStatus.CREATED);
    }
}
