package com.tms.service;

import com.tms.model.Book;
import com.tms.model.Client;
import com.tms.model.ROLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class StartupService {
    private final ClientService clientService;
    private final BookService bookService;

    @Autowired
    public StartupService(ClientService clientService, BookService bookService) {
        this.clientService = clientService;
        this.bookService = bookService;
    }

    @PostConstruct
    public void start() {
        Client client1 = new Client("Kyle", "pass1", ROLE.ADMIN);
        Client client2 = new Client("Mike", "pass2", ROLE.USER);
        Client client3 = new Client("Peter", "pass3", ROLE.USER);
        Client client4 = new Client("Andy", "pass4", ROLE.USER);
        Book it = new Book("Stiven King", "It", 3);
        Book sk112263 = new Book("Stiven King", "11/22/63", 4);
        clientService.registrationClient(client1);
        clientService.registrationClient(client2);
        clientService.registrationClient(client3);
        clientService.registrationClient(client4);
        bookService.save(it);
        bookService.save(sk112263);
        bookService.addBookToClient(it.getId(), client2.getId());
        bookService.addBookToClient(sk112263.getId(), client2.getId());
        bookService.addBookToClient(sk112263.getId(), client3.getId());
    }
}
