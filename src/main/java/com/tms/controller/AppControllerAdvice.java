package com.tms.controller;

import com.tms.exception.AddBookException;
import com.tms.exception.ReturnBookException;
import com.tms.exception.UserByIdNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(UserByIdNotFoundException.class)
    public String catchException(UserByIdNotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "admin";
    }

    @ExceptionHandler(AddBookException.class)
    public String catchException(AddBookException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "add-book-to-user";
    }

    @ExceptionHandler(ReturnBookException.class)
    public String catchException(ReturnBookException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "return-book-from-user";
    }
}
