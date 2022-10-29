package com.tms.controller;

import com.tms.model.BookProjection;
import com.tms.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/")
public class ShowBooksOfAuthorController {
    private final BookService bookService;

    public ShowBooksOfAuthorController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/authors/{author}")
    public String showBooksOfUser(@PathVariable(name = "author") String author,
                                  @RequestParam(defaultValue = "0") Integer page,
                                  Model model) {
        Page<BookProjection> pages = bookService.findByAuthorOrderByTitle(author, PageRequest.of(page, 10));
        model.addAttribute("author", author);
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("booksOfAuthor", pages.getContent());
        return "books-of-author";
    }

}
