package com.tms.controller;

import com.tms.exception.AddBookException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(AddBookException.class)
    public String catchException(AddBookException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "add-book-to-user";
    }
}
