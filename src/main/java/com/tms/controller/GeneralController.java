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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/")
public class GeneralController {
    @Value(value = "${size-of-page}")
    private int sizeOfPage;
    @Autowired
    private BookService bookService;

    @GetMapping
    public String mainPage(@RequestParam(defaultValue = "0") Integer page,
                           Model model) {
        Page<BookProjection> pages = bookService.findAllAvailableBooksWithCount(PageRequest.of(page, sizeOfPage));
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("books", pages.getContent());
        return "main-page";
    }

    @GetMapping(path = "search")
    public String search(@RequestParam String forSearch,
                         @RequestParam(defaultValue = "0") Integer page,
                         Model model) {
        Page<BookProjection> pages = bookService.findAvailableBooksFromSearch(forSearch, PageRequest.of(page, sizeOfPage));
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("books", pages.getContent());
        return "main-page";
    }

}
