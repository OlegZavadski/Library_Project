package com.tms.controller;

import com.tms.service.BookService;
import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/admin")
public class AdminController extends AbstractAdminController {
    public AdminController(UserService userService, BookService bookService, GeneralService generalService) {
        super(userService, bookService, generalService);
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        findOnlyActiveUsers(model);
        return "list-of-users-for-admin";
    }

    @GetMapping(path = "/show_all_books")
    public String showAllBooks(@RequestParam(defaultValue = "0") Integer page,
                               Model model) {
        findAllNotDeletedBooks(page, model);
        return "list-of-books-for-admin";
    }

}
