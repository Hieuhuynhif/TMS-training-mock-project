package com.example.tmstraining.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse {
    private String message;
    private int status;
    private String error;
    private final Date timestamp;

    public ErrorResponse(Date timestamp) {
        this.timestamp = timestamp;
    }

    public ErrorResponse() {
        timestamp = new Date();
    }
}
