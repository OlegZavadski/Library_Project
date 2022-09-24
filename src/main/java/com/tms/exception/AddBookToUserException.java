package com.tms.exception;

public class AddBookToUserException extends RuntimeException{
    public AddBookToUserException(String message) {
        super(message);
    }
}
