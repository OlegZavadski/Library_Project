package com.tms.controller;

import com.tms.model.Book;
import com.tms.model.ROLE;
import com.tms.model.User;
import com.tms.service.BookService;
import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/admin")
public class SaveNewBookController extends AbstractAdminController {

    public SaveNewBookController(UserService userService, BookService bookService, GeneralService generalService) {
        super(userService, bookService, generalService);
    }

    @GetMapping(path = "/save_new_book")
    public String registration() {
        return "save-new-book";
    }

    @PostMapping(path = "/save_new_book")
    public String registration(@RequestParam String author,
                               @RequestParam String title,
                               @RequestParam String year,
                               Model model) {
        if (author.isBlank() || title.isBlank() || year.isBlank()) {
            model.addAttribute("error", "Some field is empty");
            return "save-new-book";
        }
        bookService.save(new Book(author, title, Integer.parseInt(year)));
        model.addAttribute("books", bookService.findAllBooks());
        return "list-of-books-for-admin";
    }

}