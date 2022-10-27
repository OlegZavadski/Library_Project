package com.tms.controller;

import com.tms.dto.UserDto;
import com.tms.model.Book;
import com.tms.service.BookService;
import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class ShowBooksOfUserController extends AbstractAdminController {

    public ShowBooksOfUserController(UserService userService, BookService bookService, GeneralService generalService) {
        super(userService, bookService, generalService);
    }

    @GetMapping(path = "/show_books_of_user/{id}")
    public String showBooksOfUser(@PathVariable(name = "id") Integer userId,
                                  Model model) {
        UserDto userById = userService.findById(userId);
        List<Book> booksOfUser = userById.getBooks()
                .stream()
                .sorted(Comparator.comparing(Book::getId))
                .toList();
        model.addAttribute("userById", userById);
        model.addAttribute("booksOfUser", booksOfUser);
        return "books-of-user";
    }

}