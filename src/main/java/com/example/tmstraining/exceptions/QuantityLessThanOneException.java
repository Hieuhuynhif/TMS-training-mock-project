package com.example.tmstraining.exceptions;

public class QuantityLessThanOneException extends RuntimeException {
    public QuantityLessThanOneException() {
        super("Quantity less than one item");
    }
}
