package com.tms.service;

import com.tms.dto.UserDto;
import com.tms.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    UserDto findUserById(Integer id);

    boolean saveNewUser(User user);

    UserDto findUserByLogin(String login);

    void deleteUserById(Integer id);

    List<UserDto> findAllNotDeletedUsers();
}
