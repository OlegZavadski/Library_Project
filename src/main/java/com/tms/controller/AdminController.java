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

import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
    private final UserService userService;
    private final BookService bookService;
    private final GeneralService generalService;

    public AdminController(UserService userService, BookService bookService, GeneralService generalService) {
        this.userService = userService;
        this.bookService = bookService;
        this.generalService = generalService;
    }

    @GetMapping()
    public String adminPage(Model model) {
        findOnlyUsers(model);
        return "admin";
    }

    @GetMapping(path = "/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String registration(@RequestParam String login,
                               @RequestParam String password,
                               Model model) {
        if (login.isBlank() || password.isBlank()) {
            return "registration";
        }
        if (userService.findByLogin(login) != null) {
            model.addAttribute("userExists", "User with this login exists!");
            return "registration";
        }
        userService.saveNewUser(new User(login, password, ROLE.ROLE_USER));
        findOnlyUsers(model);
        return "admin";
    }

    @GetMapping(path = "/show_books_of_user")
    public String showBooksOfUser(@RequestParam Integer idOfUser, Model model) {
        User userById = userService.findById(idOfUser);
        List<Book> booksOfUser = userById.getBooks();
        model.addAttribute("userById", userById);
        model.addAttribute("booksOfUser", booksOfUser);
        return "books-of-user";
    }

    @PostMapping(path = "/delete")
    public String delete(@RequestParam Integer idToDelete,
                         Model model) {
        userService.delete(idToDelete);
        findOnlyUsers(model);
        return "admin";
    }

    @GetMapping(path = "/add_book_to_user")
    public String addBookToUser(@RequestParam Integer idOfUser,
                                Model model) {
        model.addAttribute("userById", userService.findById(idOfUser));
        model.addAttribute("allBooks", bookService.showAllBooks());
        return "add-book-to-user";
    }

    @PostMapping(path = "/add_book_to_user")
    public String addBookToUser(@RequestParam Integer idOfBook,
                                @RequestParam Integer idOfUser,
                                Model model) {
        generalService.addBookToUser(idOfBook, idOfUser);
        findOnlyUsers(model);
        return "admin";
    }

    @PostMapping(path = "/return_book_from_user")
    public String returnBookFromUser(@RequestParam Integer idOfBook,
                                     @RequestParam Integer idOfUser,
                                     Model model) {
        generalService.returnBookFromUser(idOfBook, idOfUser);
        findOnlyUsers(model);
        return "admin";
    }

    @GetMapping(path = "/show_users_of_book")
    public String usersOfBook(@RequestParam Integer idOfBook,
                              Model model) {
        Book bookById = bookService.findById(idOfBook);
        model.addAttribute("bookById", bookById);
        model.addAttribute("usersOfBook", bookById.getUsers());
        return "users-of-book";
    }

    private void findOnlyUsers(Model model) {
        model.addAttribute("allUsers", userService.findOnlyUsers());
    }
}
