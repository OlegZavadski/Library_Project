package com.tms.service.impl;

import com.tms.model.Book;
import com.tms.model.BookProjection;
import com.tms.repository.BookRepository;
import com.tms.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findAllNotDeletedBooks() {
        return bookRepository.findAllNotDeletedBooks();
    }

    @Override
    public List<BookProjection> findAllBooksWithCount() {
        return bookRepository.findAllBooksWithCount();
    }

    @Override
    public List<Book> findOverdueBooks() {
        return bookRepository.findOverdueBooks();
    }

    @Override
    public List<BookProjection> findByAuthorOrderByTitle(String author) {
        return bookRepository.findByAuthorOrderByTitle(author);
    }

    @Override
    public void delete(Integer id) {
        bookRepository.findById(id).ifPresent(book -> book.setAvailable(false));
        bookRepository.findById(id).ifPresent(book -> book.setDeleted(true));
    }
}
