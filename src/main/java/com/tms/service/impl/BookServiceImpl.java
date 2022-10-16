package com.tms.service.impl;

import com.tms.model.Book;
import com.tms.repository.BookRepository;
import com.tms.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
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

    public List<Book> showAllBooks() {
        return bookRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Book::getId))
                .toList();
    }

    @Override
    public List<Book> showOverdueBooks() {
        return bookRepository
                .findAll()
                .stream()
                .filter(book -> book.getDateOfIssue() != null)
                .filter(book -> (new Date().getTime() - book.getDateOfIssue().getTime()) > 20 * 24 * 60 * 60 * 1000)
                .sorted(Comparator.comparing(Book::getDateOfIssue))
                .toList();
    }
}
