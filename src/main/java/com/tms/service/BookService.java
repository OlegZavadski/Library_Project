package com.tms.service;

import com.tms.model.Book;

import java.util.List;

public interface BookService {
    void save(Book book);

    Book getById(Integer id);

    void addBookToClient(Integer idOfBook, Integer idOfClient);

    void returnBookFromClient(Integer idBook, Integer idClient);

    List<Book> showAllBooks();
}
