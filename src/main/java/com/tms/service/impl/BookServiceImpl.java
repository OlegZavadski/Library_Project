package com.tms.service.impl;

import com.tms.model.Book;
import com.tms.model.BookProjection;
import com.tms.repository.BookRepository;
import com.tms.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Book> findAllNotDeletedBooks(Pageable pageable) {
        return bookRepository.findAllNotDeletedBooks(pageable);
    }

    @Override
    public Page<BookProjection> findAllBooksWithCount(Pageable pageable) {
        return bookRepository.findAllBooksWithCount(pageable);
    }

    @Override
    public List<Book> findOverdueBooks() {
        return bookRepository.findOverdueBooks();
    }

    @Override
    public Page<BookProjection> findByAuthorOrderByTitle(String author, Pageable pageable) {
        return bookRepository.findByAuthorOrderByTitle(author, pageable);
    }

    @Override
    public void delete(Integer id) {
        bookRepository.findById(id).ifPresent(book -> book.setAvailable(false));
        bookRepository.findById(id).ifPresent(book -> book.setDeleted(true));
    }

    @Override
    public Page<BookProjection> getBooksFromSearch(String forSearch, Pageable pageable) {
        return bookRepository.getBooksFromSearch("%" + forSearch + "%", pageable);
    }

    @Override
    public Page<Book> findAvailableBooksForAddToUser(Pageable pageable) {
        return bookRepository.findAvailableBooksForAddToUser(pageable);
    }
}
