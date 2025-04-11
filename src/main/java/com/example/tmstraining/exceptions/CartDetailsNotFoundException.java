package com.example.tmstraining.exceptions;

public class CartDetailsNotFoundException extends RuntimeException {
    public CartDetailsNotFoundException() {
        super("Card details not found");
    }
}
