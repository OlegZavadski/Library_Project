package com.tms.controller;

import com.tms.dto.UserDto;
import com.tms.model.Book;
import com.tms.model.ROLE;
import com.tms.model.User;
import com.tms.service.BookService;
import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
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
            model.addAttribute("error", "Some field is empty");
            return "registration";
        }
        if (userService.findByLogin(login) != null) {
            model.addAttribute("error", "User with this login exists!");
            return "registration";
        }
        userService.saveNewUser(new User(login, password, ROLE.ROLE_USER));
        findOnlyUsers(model);
        return "admin";
    }

    @GetMapping(path = "/{id}")
    public String showBooksOfUser(@PathVariable(name = "id") Integer idOfUser,
                                  Model model) {
        UserDto userById = userService.findById(idOfUser);
        List<Book> booksOfUser = userById.getBooks()
                .stream()
                .sorted(Comparator.comparing(Book::getId))
                .toList();
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

    @GetMapping(path = "/show_overdue_books")
    public String overdueBooks(Model model) {
        List<Book> books = bookService.showOverdueBooks();
        model.addAttribute("books", books);
        return "overdue-books";
    }

    private void findOnlyUsers(Model model) {
        model.addAttribute("allUsers", userService.findOnlyActiveUsers());
    }
}
