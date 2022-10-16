package com.tms.service;

import com.tms.dto.UserDto;
import com.tms.model.User;

import java.util.List;

public interface UserService {
    UserDto findById(Integer id);

    void save(User user);

    void saveNewUser(User user);

    UserDto findByLogin(String login);

    void delete(Integer id);

    List<UserDto> findOnlyUsers();
}
