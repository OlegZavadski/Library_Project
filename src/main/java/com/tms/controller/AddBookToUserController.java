package com.tms.controller;

import com.tms.model.Book;
import com.tms.service.BookService;
import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public String addBookToUser(@PathVariable(name = "id") Integer userId,
                                @RequestParam(name = "page", defaultValue = "0") Integer page,
                                Model model) {
        model.addAttribute("userById", userService.findUserById(userId));
        Page<Book> pages = bookService.findAvailableBooksToAddToUser(PageRequest.of(page, sizeOfPage));
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("books", pages.getContent());
//        findAllNotDeletedBooks(page, model);
        return "add-book-to-user";
    }

    @PostMapping(path = "/add_book_to_user")
    public String addBookToUserAndGetListUsers(@RequestParam Integer bookId,
                                               @RequestParam Integer userId,
                                               Model model) {
        generalService.addBookToUser(bookId, userId);
        findOnlyActiveUsers(model);
        return "list-of-users-for-admin";
    }

}
