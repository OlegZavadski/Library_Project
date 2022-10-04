package com.tms.service;

public interface GeneralService {
    void addBookToUser(Integer idOfBook, Integer idOfUser);

    void returnBookFromUser(Integer idOfBook, Integer idOfUser);
}
