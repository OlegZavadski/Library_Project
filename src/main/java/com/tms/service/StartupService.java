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

    @Autowired
    public StartupService(ClientService clientService, BookService bookService) {
        this.clientService = clientService;
        this.bookService = bookService;
    }

    @PostConstruct
    public void start() {
        Client client1 = new Client("Kyle", "$2a$12$GnJpZ7gxaV80Qp8OmudFueJT/fTJ8VSEYRZdMjReYFF8lgDJ8pMYy", ROLE.ROLE_ADMIN);
        Client client2 = new Client("Mike", "$2a$12$qDZToqB3qu7XZSGH2W.lGu1EFc5CiAn7N3qrpwfTxAHCcRWu6pOGa", ROLE.ROLE_USER);
        Client client3 = new Client("Peter", "$2a$12$1Z28A1RrBYriQEyoFdX9y.YikH51E7eyXViT6k2TIvRTviKGIwZ3.", ROLE.ROLE_USER);
        Client client4 = new Client("Andy", "$2a$12$e7vTGGbnyHCl9tWH4DNoNOZUvnJU6xzOaLigwlNoAEByVQnTjzLkm", ROLE.ROLE_USER);
        List<Client> users = List.of(client1, client2, client3, client4);
        List<String> loginsFromDb = clientService
                .findAllUsers()
                .stream()
                .map(Client::getLogin)
                .toList();
        users.forEach(client -> {
            if (!loginsFromDb.contains(client.getLogin())) {
                clientService.registrationClient(client);
            }
        });
        Book it = new Book("Stiven King", "It", 3);
        Book sk112263 = new Book("Stiven King", "11/22/63", 4);
        bookService.save(it);
        bookService.save(sk112263);
        bookService.addBookToClient(it.getId(), client2.getId());
        bookService.addBookToClient(sk112263.getId(), client2.getId());
        bookService.addBookToClient(sk112263.getId(), client3.getId());
    }
}
