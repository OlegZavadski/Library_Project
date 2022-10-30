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
    public Page<BookProjection> findAllAvailableBooksWithCount(Pageable pageable) {
        return bookRepository.findAllAvailableBooksWithCount(pageable);
    }

    @Override
    public List<Book> findOverdueBooks() {
        return bookRepository.findOverdueBooks();
    }

    @Override
    public Page<BookProjection> findBooksByAuthor(String author, Pageable pageable) {
        return bookRepository.findBooksByAuthor(author, pageable);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookRepository.findById(id).ifPresent(book -> book.setAvailable(false));
        bookRepository.findById(id).ifPresent(book -> book.setDeleted(true));
    }

    @Override
    public Page<BookProjection> findAvailableBooksFromSearch(String forSearch, Pageable pageable) {
        return bookRepository.findAvailableBooksFromSearch("%" + forSearch + "%", pageable);
    }

    @Override
    public Page<Book> findAvailableBooksToAddToUser(Pageable pageable) {
        return bookRepository.findAvailableBooksToAddToUser(pageable);
    }
}
