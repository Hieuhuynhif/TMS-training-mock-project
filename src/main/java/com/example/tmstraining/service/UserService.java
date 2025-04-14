package com.example.tmstraining.service;

import com.example.tmstraining.dtos.user.UserDTO;
import com.example.tmstraining.dtos.user.UserMapper;
import com.example.tmstraining.entities.Cart;
import com.example.tmstraining.entities.User;
import com.example.tmstraining.enums.Role;
import com.example.tmstraining.exception.IncorrectPasswordException;
import com.example.tmstraining.exception.UserExistedException;
import com.example.tmstraining.repository.CartRepository;
import com.example.tmstraining.repository.UserRepository;
import com.example.tmstraining.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, CartRepository cartRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(User user) {
        User userDetails = userRepository.findByUsername(user.getUsername());

        if (userDetails == null) {
            throw new IncorrectPasswordException();
        }

        return JwtUtil.generateToken(UserMapper.INSTANCE.toDTO(userDetails));
    }

    @Transactional
    public UserDTO signup(User user) {
        User userDetails = userRepository.findByUsername(user.getUsername());
        if (userDetails != null) {
            throw new UserExistedException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);

        User newUser = userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(newUser);
        cartRepository.save(cart);
        return UserMapper.INSTANCE.toDTO(newUser);
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
