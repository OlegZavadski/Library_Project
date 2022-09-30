package com.tms.controller;

import com.tms.model.Book;
import com.tms.model.Client;
import com.tms.model.ROLE;
import com.tms.service.BookService;
import com.tms.service.ClientService;
import com.tms.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ClientService clientService;
    private final BookService bookService;
    private final GeneralService generalService;


    @Autowired
    public AdminController(ClientService clientService, BookService bookService, GeneralService generalService) {
        this.clientService = clientService;
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
        clientService.saveNewClient(new Client(login, password, ROLE.ROLE_USER));
        findOnlyUsers(model);
        return "admin";
    }

    @GetMapping(path = "/show_books_of_client")
    public String showBooksOfClient(@RequestParam Integer idOfClient, Model model) {
        Client clientById = clientService.findById(idOfClient);
        List<Book> booksOfClient = clientById.getBooks();
        model.addAttribute("clientById", clientById);
        model.addAttribute("booksOfClient", booksOfClient);
        return "books-of-client";
    }

    @PostMapping(path = "/delete")
    public String delete(@RequestParam Integer idToDelete,
                         Model model) {
        clientService.delete(idToDelete);
        findOnlyUsers(model);
        return "admin";
    }

    @GetMapping(path = "/show_all_books")
    public String showAllBooks(Model model) {
        model.addAttribute("allBooks", bookService.showAllBooks());
        return "all-books";
    }

    @GetMapping(path = "/add_book_to_user")
    public String addBookToUser(@RequestParam Integer idOfClient,
                                Model model) {
        Client clientById = clientService.findById(idOfClient);
        model.addAttribute("clientById", clientById);
        model.addAttribute("allBooks", bookService.showAllBooks());
        return "add-book-to-user";
    }

    @PostMapping(path = "/add_book_to_user")
    public String addBookToUser(@RequestParam Integer idOfBook,
                                @RequestParam Integer idOfClient,
                                Model model) {
        generalService.addBookToClient(idOfBook, idOfClient);
        findOnlyUsers(model);
        return "admin";
    }

    @PostMapping(path = "/return_book_from_client")
    public String returnBookFromUser(@RequestParam Integer idOfBook,
                                     @RequestParam Integer idOfClient,
                                     Model model) {
        generalService.returnBookFromClient(idOfBook, idOfClient);
        findOnlyUsers(model);
        return "admin";
    }

    private void findOnlyUsers(Model model) {
        model.addAttribute("allUsers", clientService.findOnlyUsers());
    }
}
