package com.tms.service;

import com.tms.model.Book;
import com.tms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> showAllBooks() {
        return bookRepository.findAll();
    }
}
