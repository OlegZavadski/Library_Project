package com.tms.controller;

import com.tms.service.BookService;
import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/admin")
public class ReturnBookFromUserController extends AbstractAdminController {

    public ReturnBookFromUserController(UserService userService, BookService bookService, GeneralService generalService) {
        super(userService, bookService, generalService);
    }

    @PostMapping(path = "/return_book_from_user")
    public String returnBookFromUser(@RequestParam Integer idOfBook,
                                     @RequestParam Integer idOfUser,
                                     Model model) {
        generalService.returnBookFromUser(idOfBook, idOfUser);
        findOnlyActiveUsers(model);
        return "admin";
    }
}
