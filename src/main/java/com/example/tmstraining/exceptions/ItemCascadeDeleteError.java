package com.example.tmstraining.exceptions;

public class ItemCascadeDeleteError extends RuntimeException {
    public ItemCascadeDeleteError() {
        super("Item cascade delete error");
    }
}
