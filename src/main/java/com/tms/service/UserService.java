package com.tms.service;

import com.tms.model.User;

import java.util.List;

public interface UserService {
    User findById(Integer id);

    void save(User user);

    void saveNewUser(User user);

    User findByLogin(String login);

    void delete(Integer id);

    List<User> findOnlyUsers();
}
