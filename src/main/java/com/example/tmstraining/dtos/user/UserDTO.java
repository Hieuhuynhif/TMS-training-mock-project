package com.example.tmstraining.dtos.user;


import com.example.tmstraining.enums.Role;

public record UserDTO(int id, String username, Role role) {
}
