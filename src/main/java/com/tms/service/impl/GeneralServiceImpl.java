package com.tms.service.impl;

import com.tms.model.Book;
import com.tms.model.User;
import com.tms.repository.BookRepository;
import com.tms.repository.UserRepository;
import com.tms.service.GeneralService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GeneralServiceImpl implements GeneralService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public GeneralServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addBookToUser(Integer bookId, Integer userId) {
        Book bookById = bookRepository.findById(bookId).get();
        User userById = userRepository.findById(userId).get();
        bookById.setAvailable(false);
        bookById.setDateOfIssue(new Date());
        userById.getBooks().add(bookById);
        userById.setUpdated(new Date());
        bookRepository.save(bookById);
        userRepository.save(userById);
    }

    @Override
    public void returnBookFromUser(Integer bookId, Integer userId) {
        Book bookById = bookRepository.findById(bookId).get();
        User userById = userRepository.findById(userId).get();
        bookById.setAvailable(true);
        bookById.setDateOfIssue(null);
        userById.getBooks().remove(bookById);
        userById.setUpdated(new Date());
        bookRepository.save(bookById);
        userRepository.save(userById);
    }
}
