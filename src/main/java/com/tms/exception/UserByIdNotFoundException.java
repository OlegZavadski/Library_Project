package com.tms.exception;

public class UserByIdNotFoundException extends RuntimeException{
    public UserByIdNotFoundException(String message) {
        super(message);
    }
}
