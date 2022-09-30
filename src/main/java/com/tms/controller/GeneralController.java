package com.tms.controller;

import com.tms.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class GeneralController {
    private final BookService bookService;

    public GeneralController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("books", bookService.showAllBooks());
        return "main-page";
    }

    @GetMapping(path = "login")
    public String authorization() {
        return "login";
    }
}
