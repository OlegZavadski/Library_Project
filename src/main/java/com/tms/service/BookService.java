package com.tms.service;

import com.tms.model.Book;
import com.tms.model.BookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    void save(Book book);

    Book findById(Integer id);

    List<Book> findAllNotDeletedBooks();

    Page<BookProjection> findAllBooksWithCount(Pageable pageable);

    List<Book> findOverdueBooks();

    List<BookProjection> findByAuthorOrderByTitle(String author);

    void delete(Integer id);

    Page<BookProjection> getBooksFromSearch(String forSearch, Pageable pageable);
}
