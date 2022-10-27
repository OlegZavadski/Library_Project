package com.tms.service;

import com.tms.model.Book;
import com.tms.model.BookProjection;

import java.util.List;

public interface BookService {
    void save(Book book);

    Book findById(Integer id);

    List<Book> findAllNotDeletedBooks();

    List<BookProjection> findAllBooksWithCount();

    List<Book> findOverdueBooks();

    List<BookProjection> findByAuthorOrderByTitle(String author);
    void delete(Integer id);
}
