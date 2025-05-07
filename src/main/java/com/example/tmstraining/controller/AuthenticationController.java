package com.example.tmstraining.controller;

import com.example.tmstraining.dtos.user.UserDTO;
import com.example.tmstraining.entities.User;
import com.example.tmstraining.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("login")
    public UserDTO login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("signup")
    public UserDTO signup(@RequestBody User user) {
        return userService.signup(user);
    }

}
