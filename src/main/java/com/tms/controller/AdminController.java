package com.tms.controller;

import com.tms.exception.AddBookToUserException;
import com.tms.exception.UserByIdNotFoundException;
import com.tms.model.Book;
import com.tms.model.Client;
import com.tms.model.ROLE;
import com.tms.service.BookService;
import com.tms.service.ClientService;
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


    @Autowired
    public AdminController(ClientService clientService, BookService bookService) {
        this.clientService = clientService;
        this.bookService = bookService;
    }
    
//    @PostMapping(path = "/check")
//    public String check(@RequestParam(value = "login") String login,
//                        @RequestParam(value = "password") String password,
//                        Model model) {
//        Client clientByLoginAndPassword = clientService.findByLoginAndPassword(login, password);
//        if (clientByLoginAndPassword == null) {
//            throw new UserByLoginAndPasswordNotFoundException("This user not found");
//        }
//        if (clientByLoginAndPassword.getRole().equals(ROLE.ADMIN)) {
//            return "admin";
//        } else {
//            List<Book> booksOfUser = clientService.getAllBooks(clientByLoginAndPassword.getId());
//            model.addAttribute("books", booksOfUser);
//            return "user";
//        }
//    }

    @GetMapping(path = "/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String registration(@RequestParam String login,
                               @RequestParam String password,
                               Model model) {
        clientService.registrationClient(new Client(login, password, ROLE.USER));
        findAllUsers(model);
        return "admin";
    }

    @GetMapping(path = "/show_all_users")
    public String showAllUsers(Model model) {
        findAllUsers(model);
        return "admin";
    }

    @PostMapping(path = "/delete")
    public String delete(@RequestParam Integer idToDelete,
                         Model model) {
        if (clientService.getById(idToDelete) == null) {
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
        Book bookById = bookService.getById(idOfBook);
        Client clientById = clientService.getById(idOfClient);
        if (bookById == null) {
            throw new AddBookToUserException("Book with this id not found");
        }
        if (clientById == null) {
            throw new AddBookToUserException("User with this id not found");
        }
        if (bookById != null) {
            if (bookById.getCount() < 1) {
                throw new AddBookToUserException("You don't have enough books");
            }
        }
        if (clientById != null) {
            if (clientById.getRole().equals(ROLE.ADMIN)) {
                throw new AddBookToUserException("You can't add a book to an administrator");
            }
            if (clientById.getBooks().size() == 4) {
                throw new AddBookToUserException("The client has 4 books");
            }
        }
        bookService.addBookToClient(idOfBook, idOfClient);
        return "admin";
    }

    private void findAllUsers(Model model) {
        List<Client> allUsers = clientService.findAllUsers();
        model.addAttribute("allUsers", allUsers);
    }
}
