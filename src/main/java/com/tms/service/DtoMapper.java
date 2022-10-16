package com.tms.service;

import com.tms.dto.UserDto;
import com.tms.model.User;
import org.springframework.stereotype.Service;

@Service
public class DtoMapper {
    public UserDto createUserDto(User userFromDb) {
        return new UserDto(userFromDb.getId(),
                userFromDb.getLogin(),
                userFromDb.getPassword(),
                userFromDb.getRole(),
                userFromDb.getCreated(),
                userFromDb.getUpdated(),
                userFromDb.getBooks());
    }
}
