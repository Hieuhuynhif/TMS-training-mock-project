package com.example.tmstraining.dtos.user;


import com.example.tmstraining.enums.Role;
import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String username;
    private Role role;
}
