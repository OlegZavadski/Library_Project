package com.tms.service.impl;

import com.tms.model.Book;
import com.tms.model.User;
import com.tms.service.BookService;
import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GeneralServiceImpl implements GeneralService {
    private final BookService bookService;
    private final UserService userService;

    public GeneralServiceImpl(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    public void addBookToUser(Integer idOfBook, Integer idOfUser) {
        Book bookById = bookService.findById(idOfBook);
        User userById = userService.findById(idOfUser);
        bookById.setCount(bookById.getCount() - 1);
        userById.getBooks().add(bookById);
        userById.setUpdated(new Date());
    }

    public void returnBookFromUser(Integer idOfBook, Integer idOfUser) {
        Book bookById = bookService.findById(idOfBook);
        User userById = userService.findById(idOfUser);
        bookById.setCount(bookById.getCount() + 1);
        userById.getBooks().remove(bookById);
        userById.setUpdated(new Date());
    }
}
