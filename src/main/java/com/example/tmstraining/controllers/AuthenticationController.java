package com.example.tmstraining.controllers;

import com.example.tmstraining.dtos.user.UserDTO;
import com.example.tmstraining.entities.User;
import com.example.tmstraining.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("signup")
    public UserDTO signup(@RequestBody User user) {
        return userService.signup(user);
    }

}
