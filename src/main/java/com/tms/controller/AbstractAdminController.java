package com.tms.controller;

import com.tms.model.Book;
import com.tms.service.BookService;
import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public abstract class AbstractAdminController {
    protected static final Integer sizeOfPage = 10;
    protected final UserService userService;
    protected final BookService bookService;
    protected final GeneralService generalService;

    public AbstractAdminController(UserService userService, BookService bookService, GeneralService generalService) {
        this.userService = userService;
        this.bookService = bookService;
        this.generalService = generalService;
    }

    protected void findOnlyActiveUsers(Model model) {
        model.addAttribute("allUsers", userService.findAllNotDeletedUsers());
    }

    protected void findAllNotDeletedBooks(Integer page,
                                          Model model) {
        Page<Book> pages = bookService.findAllNotDeletedBooks(PageRequest.of(page, sizeOfPage));
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("books", pages.getContent());
    }

}
