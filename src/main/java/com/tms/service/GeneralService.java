package com.tms.service;

import com.tms.model.Book;
import com.tms.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GeneralService {
    private final BookService bookService;
    private final UserService userService;

    public GeneralService(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    public void addBookToUser(Integer idOfBook, Integer idOfUser) {
        Book bookById = bookService.findById(idOfBook);
        User userById = userService.findById(idOfUser);
        bookById.setCount(bookById.getCount() - 1);
        List<Book> books = userById.getBooks();
        books.add(bookById);
        userById.setBooks(books);
        userById.setUpdated(new Date());
    }

    public void returnBookFromUser(Integer idOfBook, Integer idOfUser) {
        Book bookById = bookService.findById(idOfBook);
        User userById = userService.findById(idOfUser);
        bookById.setCount(bookById.getCount() + 1);
        List<Book> books = userById.getBooks();
        books.remove(bookById);
        userById.setBooks(books);
        userById.setUpdated(new Date());
    }
}
