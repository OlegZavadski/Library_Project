package com.tms.controller;

import com.tms.model.BookProjection;
import com.tms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value(value = "${page-size}")
    private int pageSize;
    @Autowired
    private BookService bookService;

    @GetMapping(path = "/authors/{author}")
    public String showBooksOfUser(@PathVariable(name = "author") String author,
                                  @RequestParam(defaultValue = "0") Integer page,
                                  Model model) {
        Page<BookProjection> pages = bookService.findBooksByAuthor(author, PageRequest.of(page, pageSize));
        model.addAttribute("author", author);
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("booksOfAuthor", pages.getContent());
        return "books-of-author";
    }

}
