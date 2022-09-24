package com.tms.service.impl;

import com.tms.model.Book;
import com.tms.model.Client;
import com.tms.repository.ClientRepository;
import com.tms.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getById(Integer id) {
        return clientRepository.getById(id);
    }

    @Override
    public void registrationClient(Client client) {
        clientRepository.registrationClient(client);
    }

    @Override
    public Client findByLoginAndPassword(String login, String password) {
        return clientRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public Client findByLogin(String login) {
        return clientRepository.findByLogin(login);
    }

    @Override
    public List<Client> findAllUsers() {
        return clientRepository.findAllUsers();
    }

    @Override
    public void delete(Integer id) {
        clientRepository.delete(id);
    }

    @Override
    public List<Book> getAllBooks(Integer id) {
        return clientRepository.getAllBooks(id);
    }
}
