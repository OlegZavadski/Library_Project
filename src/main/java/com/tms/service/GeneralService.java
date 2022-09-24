package com.tms.service;

import com.tms.model.Book;
import com.tms.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralService {
    private final BookService bookService;
    private final ClientService clientService;

    public GeneralService(BookService bookService, ClientService clientService) {
        this.bookService = bookService;
        this.clientService = clientService;
    }

    public void addBookToClient(Integer idOfBook, Integer idOfClient) {
        Book bookById = bookService.findById(idOfBook);
        Client clientById = clientService.findById(idOfClient);
        bookById.setCount(bookById.getCount() - 1);
        List<Book> books = clientById.getBooks();
        books.add(bookById);
        clientById.setBooks(books);
        bookService.save(bookById);
        clientService.save(clientById);
    }

    public void returnBookFromClient(Integer idOfBook, Integer idOfClient) {
        Book bookById = bookService.findById(idOfBook);
        Client clientById = clientService.findById(idOfClient);
        bookById.setCount(bookById.getCount() + 1);
        List<Book> books = clientById.getBooks();
        books.remove(bookById);
        clientById.setBooks(books);
        bookService.save(bookById);
        clientService.save(clientById);
    }
}
