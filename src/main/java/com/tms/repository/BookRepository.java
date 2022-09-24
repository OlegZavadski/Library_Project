package com.tms.repository;


import com.tms.model.Book;

import java.util.List;

public interface BookRepository {
    void save(Book book);

    Book getById(Integer id);

    void addBookToClient(Integer idOfBook, Integer idOfClient);

    void returnBookFromClient(Integer idBook, Integer idClient);

    List<Book> showAllBooks();
}
