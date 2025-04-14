package com.example.tmstraining.exception;

public class UserExistedException extends RuntimeException {
    public UserExistedException() {
        super("User is already existed");
    }
}
