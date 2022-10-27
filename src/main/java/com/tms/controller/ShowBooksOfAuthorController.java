package com.tms.controller;

import com.tms.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class ShowBooksOfAuthorController {
    private final BookService bookService;

    public ShowBooksOfAuthorController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/authors/{author}")
    public String showBooksOfUser(@PathVariable(name = "author") String author,
                                  Model model) {
        model.addAttribute("author", author);
        model.addAttribute("booksOfAuthor", bookService.findByAuthorOrderByTitle(author));
        return "books-of-author";
    }

}
