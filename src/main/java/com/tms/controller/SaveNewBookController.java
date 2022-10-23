package com.tms.controller;

import com.tms.model.Book;
import com.tms.service.BookService;
import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

@Controller
@RequestMapping(path = "/admin")
public class SaveNewBookController extends AbstractAdminController {

    public SaveNewBookController(UserService userService, BookService bookService, GeneralService generalService) {
        super(userService, bookService, generalService);
    }

    @GetMapping(path = "/save_new_book")
    public String saveNewBook() {
        return "save-new-book";
    }

    @PostMapping(path = "/save_new_book")
    public String saveNewBook(@RequestParam String author,
                              @RequestParam String title,
                              @RequestParam(defaultValue = "0") Integer year,
                              Model model) {
        if (author.isBlank() || title.isBlank()) {
            model.addAttribute("error", "Some field is empty");
            return "save-new-book";
        }
        if (year <= 0 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            model.addAttribute("error", "Year should be between 1 and " + Calendar.getInstance().get(Calendar.YEAR));
            return "save-new-book";
        }
        bookService.save(new Book(author, title, year));
        model.addAttribute("books", bookService.findAllBooks());
        return "list-of-books-for-admin";
    }

}