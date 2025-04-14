package com.example.tmstraining.exception;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException() {
        super("Cart is empty");
    }
}
