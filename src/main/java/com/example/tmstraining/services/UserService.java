package com.example.tmstraining.services;

import com.example.tmstraining.Util.JwtUtil;
import com.example.tmstraining.dtos.user.UserDTO;
import com.example.tmstraining.dtos.user.UserMapper;
import com.example.tmstraining.entities.User;
import com.example.tmstraining.exceptions.IncorrectPasswordException;
import com.example.tmstraining.exceptions.UserExistedException;
import com.example.tmstraining.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(User user) {
        User userDetails = userRepository.findByUsername(user.getUsername());

        if (userDetails == null) {
            throw new IncorrectPasswordException();
        }

        return JwtUtil.generateToken(UserMapper.INSTANCE.toDTO(userDetails));
    }

    public UserDTO signup(User user) {
        User userDetails = userRepository.findByUsername(user.getUsername());
        if (userDetails != null) {
            throw new UserExistedException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return UserMapper.INSTANCE.toDTO(userRepository.save(user));
    }

    public void addUser(User user) {
        User savedUser = userRepository.findByUsername(user.getUsername());
        if (savedUser != null) {
            throw new UserExistedException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
