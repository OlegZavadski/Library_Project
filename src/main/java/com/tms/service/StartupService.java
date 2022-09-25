package com.tms.service;

import com.tms.model.Book;
import com.tms.model.Client;
import com.tms.model.ROLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class StartupService {
    private final ClientService clientService;
    private final BookService bookService;
    private final GeneralService generalService;

    @Autowired
    public StartupService(ClientService clientService, BookService bookService, GeneralService generalService) {
        this.clientService = clientService;
        this.bookService = bookService;
        this.generalService = generalService;
    }

    @PostConstruct
    public void start() {
        Client client1 = new Client("Kyle", "pass1", ROLE.ROLE_ADMIN);
        Client client2 = new Client("Mike", "pass2", ROLE.ROLE_USER);
        Client client3 = new Client("Peter", "pass3", ROLE.ROLE_USER);
        Client client4 = new Client("Andy", "pass4", ROLE.ROLE_USER);
        List<Client> users = List.of(client1, client2, client3, client4);
        List<String> loginsFromDb = clientService
                .findAll()
                .stream()
                .map(Client::getLogin)
                .toList();
        users.forEach(client -> {
            if (!loginsFromDb.contains(client.getLogin())) {
                clientService.saveNewClient(client);
            }
        });
        Book it = new Book("Stiven King", "It", 3);
        Book sk112263 = new Book("Stiven King", "11/22/63", 4);
        bookService.save(it);
        bookService.save(sk112263);
        generalService.addBookToClient(it.getId(), client2.getId());
        generalService.addBookToClient(sk112263.getId(), client2.getId());
        generalService.addBookToClient(sk112263.getId(), client3.getId());
    }
}
