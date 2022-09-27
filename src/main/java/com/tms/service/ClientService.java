package com.tms.service;

import com.tms.model.Book;
import com.tms.model.Client;
import com.tms.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    public void saveNewClient(Client client) {
        String encodedPassword = new BCryptPasswordEncoder().encode(client.getPassword());
        client.setPassword(encodedPassword);
        clientRepository.save(client);
    }

    public Client findByLogin(String login) {
        return clientRepository.findByLogin(login);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public void delete(Integer id) {
        clientRepository.deleteById(id);
    }

    public List<Book> getAllBooksFromClient(Integer id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) {
            return null;
        }
        return client.getBooks();
    }

    public List<Client> findOnlyUsers(String role) {
        return clientRepository.findOnlyUsers(role);
    }
}
