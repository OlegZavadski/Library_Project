package com.tms.service;

import com.tms.model.Book;

import java.util.List;

public interface BookService {
    void save(Book book);
    Book findById(Integer id);
    List<Book> showAllBooks();
    List<Book> showOverdueBooks();
}
