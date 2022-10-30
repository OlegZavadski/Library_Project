package com.tms.service;

import com.tms.model.Book;
import com.tms.model.BookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    void save(Book book);

    Book findById(Integer id);

    Page<Book> findAllNotDeletedBooks(Pageable pageable);

    Page<BookProjection> findAllBooksWithCount(Pageable pageable);

    List<Book> findOverdueBooks();

    Page<BookProjection> findBooksByAuthor(String author, Pageable pageable);

    void deleteBookById(Integer id);

    Page<BookProjection> findAvailableBooksFromSearch(String forSearch, Pageable pageable);

    Page<Book> findAvailableBooksToAddToUser(Pageable pageable);
}
