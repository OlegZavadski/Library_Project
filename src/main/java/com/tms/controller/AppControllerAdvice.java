package com.tms.controller;

import com.tms.exception.TransferBookException;
import com.tms.exception.UserByIdNotFoundException;
import com.tms.exception.UserByLoginAndPasswordNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppControllerAdvice {
    @ExceptionHandler(UserByLoginAndPasswordNotFoundException.class)
    public String catchException(UserByLoginAndPasswordNotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "authorization";
    }

    @ExceptionHandler(UserByIdNotFoundException.class)
    public String catchException(UserByIdNotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "admin";
    }

    @ExceptionHandler(TransferBookException.class)
    public String catchException(TransferBookException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "add_book_to_user";
    }
}
