package com.example.tmstraining.exception;

public class ItemCascadeDeleteException extends RuntimeException {
    public ItemCascadeDeleteException(String message) {
        super("Cascade delete is disable: Item is already in the " + message);
    }
}
