package com.example.tmstraining.exception;

public class CartDetailsNotFoundException extends RuntimeException {
    public CartDetailsNotFoundException() {
        super("Card details not found");
    }
}
