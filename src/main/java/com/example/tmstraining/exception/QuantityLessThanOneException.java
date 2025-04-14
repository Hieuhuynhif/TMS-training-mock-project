package com.example.tmstraining.exception;

public class QuantityLessThanOneException extends RuntimeException {
    public QuantityLessThanOneException() {
        super("Quantity less than one item");
    }
}
