package com.example.tmstraining.exception;

public class PaymentFailureException extends RuntimeException {
    public PaymentFailureException() {
        super("Payment failed");
    }
}
