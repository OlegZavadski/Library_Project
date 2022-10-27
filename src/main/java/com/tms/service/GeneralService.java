package com.tms.service;

public interface GeneralService {
    void addBookToUser(Integer bookId, Integer userId);

    void returnBookFromUser(Integer bookId, Integer userId);
}
