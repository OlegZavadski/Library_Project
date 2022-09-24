package com.tms.service;

import com.tms.model.Book;
import com.tms.model.Client;

import java.util.List;

public interface ClientService {
    Client getById(Integer id);

    void registrationClient(Client client);

    Client findByLoginAndPassword(String login, String password);

    Client findByLogin(String login);

    List<Client> findAllUsers();

    void delete(Integer id);

    List<Book> getAllBooks(Integer id);
}
