package com.example.tmstraining.exceptions;

public class PaymentFailureException extends RuntimeException {
    public PaymentFailureException() {
        super("Payment failed");
    }
}
