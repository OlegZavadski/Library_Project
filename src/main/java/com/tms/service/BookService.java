package com.tms.service;

import com.tms.model.Book;
import com.tms.model.BookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    void save(Book book);

    Book findById(Integer id);

    Page<Book> findAllNotDeletedBooks(Pageable pageable);

    Page<BookProjection> findAllAvailableBooksWithCount(Pageable pageable);

    Page<Book> findOverdueBooks(Pageable pageable);

    Page<BookProjection> findBooksByAuthor(String author, Pageable pageable);

    void deleteBookById(Integer id);

    Page<BookProjection> findAvailableBooksFromSearch(String forSearch, Pageable pageable);

    Page<Book> findAvailableBooksToAddToUser(Pageable pageable);
}
