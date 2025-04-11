package com.example.tmstraining.exceptions;

public class UserExistedException extends RuntimeException {
    public UserExistedException() {
        super("User is already existed");
    }
}
