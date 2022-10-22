package com.tms.controller;

import com.tms.service.BookService;
import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/admin")
public class AddBookToUserController extends AbstractAdminController {

    public AddBookToUserController(UserService userService, BookService bookService, GeneralService generalService) {
        super(userService, bookService, generalService);
    }

    @GetMapping(path = "/add_book_to_user/{id}")
    public String addBookToUser(@PathVariable(name = "id") Integer idOfUser,
                                Model model) {
        model.addAttribute("userById", userService.findById(idOfUser));
        model.addAttribute("allBooks", bookService.findAllBooks());
        return "add-book-to-user";
    }

    @PostMapping(path = "/add_book_to_user")
    public String addBookToUser(@RequestParam Integer idOfBook,
                                @RequestParam Integer idOfUser,
                                Model model) {
        generalService.addBookToUser(idOfBook, idOfUser);
        findOnlyActiveUsers(model);
        return "list-of-users-for-admin";
    }

}
