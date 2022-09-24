package com.tms.exception;

public class UserByLoginAndPasswordNotFoundException extends RuntimeException{
    public UserByLoginAndPasswordNotFoundException(String message) {
        super(message);
    }
}
