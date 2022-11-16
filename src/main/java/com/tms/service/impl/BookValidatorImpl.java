package com.tms.service.impl;

import com.tms.model.Book;
import com.tms.service.BookValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookValidatorImpl implements BookValidator {
    @Override
    public boolean isValidBookForSave(Book book) {
        if (book.getAuthor().isBlank() || book.getTitle().isBlank()) {
            return false;
        }
        if (book.getYear() < 1 || book.getYear() > LocalDate.now().getYear()) {
            return false;
        }
        return true;
    }
}
