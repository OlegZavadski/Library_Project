package com.tms.controller;

import com.tms.model.Book;
import com.tms.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/")
public class MainController {
    private final BookService bookService;

    public MainController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String authorization(Model model) {
        List<Book> books = bookService.showAllBooks();
        model.addAttribute("books", books);
        return "main-page";
    }

    @GetMapping(path = "/login")
    public String authorization() {
        return "login";
    }
}
