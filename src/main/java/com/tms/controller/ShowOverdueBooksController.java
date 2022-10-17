package com.tms.controller;

import com.tms.model.Book;
import com.tms.service.BookService;
import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class ShowOverdueBooksController extends AbstractAdminController {

    public ShowOverdueBooksController(UserService userService, BookService bookService, GeneralService generalService) {
        super(userService, bookService, generalService);
    }

    @GetMapping(path = "/show_overdue_books")
    public String overdueBooks(Model model) {
        List<Book> books = bookService.showOverdueBooks();
        model.addAttribute("books", books);
        return "overdue-books";
    }

}
