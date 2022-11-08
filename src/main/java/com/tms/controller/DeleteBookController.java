package com.tms.controller;

import com.tms.service.BookService;
import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/admin")
public class DeleteBookController extends AbstractAdminController {
    private final BookService bookService;

    public DeleteBookController(UserService userService, BookService bookService) {
        super(userService);
        this.bookService = bookService;
    }

    @PostMapping(path = "/delete_book")
    public String delete(@RequestParam Integer bookId,
                         Model model) {
        bookService.deleteBookById(bookId);
        findOnlyActiveUsers(model);
        return "list-of-users-for-admin";
    }

}
