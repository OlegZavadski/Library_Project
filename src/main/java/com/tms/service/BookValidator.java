package com.tms.service;

import com.tms.model.Book;

public interface BookValidator {

    boolean isValidBookForSave(Book book);

}
