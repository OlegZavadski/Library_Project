package com.tms.controller;

import com.tms.model.Book;
import com.tms.model.Client;
import com.tms.service.ClientService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    private final ClientService clientService;

    public UserController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String main(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = null;
        if (principal instanceof UserDetails) {
            login = ((UserDetails) principal).getUsername();
        } else {
            login = principal.toString();
        }
        Client clientByLogin = clientService.findByLogin(login);
        List<Book> booksOfUser = clientService.getAllBooks(clientByLogin.getId());
        model.addAttribute("books", booksOfUser);
        return "user";
    }
}
