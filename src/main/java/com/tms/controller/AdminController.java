package com.tms.controller;

import com.tms.exception.TransferBookException;
import com.tms.exception.UserByIdNotFoundException;
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
    public String adminPage() {
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
        clientService.save(new Client(login, password, ROLE.ROLE_USER));
        findAllUsers(model);
        return "admin";
    }

    @GetMapping(path = "/show_all_clients")
    public String showAllClients(Model model) {
        findAllUsers(model);
        return "admin";
    }

    @PostMapping(path = "/delete")
    public String delete(@RequestParam Integer idToDelete,
                         Model model) {
        if (clientService.findById(idToDelete) == null) {
            throw new UserByIdNotFoundException("User with this id not found");
        }
        clientService.delete(idToDelete);
        findAllUsers(model);
        return "admin";
    }

    @GetMapping(path = "/show_all_books")
    public String showAllBooks(Model model) {
        List<Book> books = bookService.showAllBooks();
        model.addAttribute("books", books);
        return "admin";
    }

    @GetMapping(path = "/add_book_to_user")
    public String addBookToUser() {
        return "add-book-to-user";
    }

    @PostMapping(path = "/add_book_to_user")
    public String addBookToUser(@RequestParam Integer idOfBook,
                                @RequestParam Integer idOfClient) {
        Book bookById = bookService.findById(idOfBook);
        Client clientById = clientService.findById(idOfClient);
        if (bookById == null) {
            throw new TransferBookException("Book with this id not found");
        }
        if (clientById == null) {
            throw new TransferBookException("User with this id not found");
        }
        if (bookById != null) {
            if (bookById.getCount() < 1) {
                throw new TransferBookException("You don't have enough books");
            }
        }
        if (clientById != null) {
            if (clientById.getRole().equals(ROLE.ROLE_ADMIN)) {
                throw new TransferBookException("You can't add a book to an administrator");
            }
            if (clientById.getBooks().size() == 4) {
                throw new TransferBookException("The client has 4 books");
            }
        }
        generalService.addBookToClient(idOfBook, idOfClient);
        return "admin";
    }

    @GetMapping(path = "/return_book_from_user")
    public String returnBookFromUser() {
        return "return-book-from-user";
    }

    @PostMapping(path = "/return_book_from_user")
    public String returnBookFromUser(@RequestParam Integer idOfBook,
                                     @RequestParam Integer idOfClient) {
        Book bookById = bookService.findById(idOfBook);
        Client clientById = clientService.findById(idOfClient);
        if (bookById == null) {
            throw new TransferBookException("Book with this id not found");
        }
        if (clientById == null) {
            throw new TransferBookException("User with this id not found");
        }
        generalService.returnBookFromClient(idOfBook, idOfClient);
        return "admin";
    }

    private void findAllUsers(Model model) {
        List<Client> allUsers = clientService.findAll();
        model.addAttribute("allUsers", allUsers);
    }
}
