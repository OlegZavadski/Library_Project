package com.tms.service.impl;

import com.tms.model.Book;
import com.tms.repository.BookRepository;
import com.tms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book getById(Integer id) {
        return bookRepository.getById(id);
    }

    @Override
    public void addBookToClient(Integer idOfBook, Integer idOfClient) {
        bookRepository.addBookToClient(idOfBook, idOfClient);
    }

    @Override
    public void returnBookFromClient(Integer idBook, Integer idClient) {
        bookRepository.returnBookFromClient(idBook, idClient);
    }

    @Override
    public List<Book> showAllBooks() {
        return bookRepository.showAllBooks();
    }
}
