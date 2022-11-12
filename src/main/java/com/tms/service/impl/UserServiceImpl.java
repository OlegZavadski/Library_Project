package com.tms.service.impl;

import com.tms.dto.UserDto;
import com.tms.model.User;
import com.tms.repository.UserRepository;
import com.tms.service.DtoMapper;
import com.tms.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DtoMapper mapper;

    public UserServiceImpl(UserRepository userRepository, DtoMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDto findUserById(Integer id) {
        User userFromDb = userRepository.findById(id).orElse(null);
        return userFromDb != null ? mapper.createUserDto(userFromDb) : null;
    }

    @Override
    public boolean saveNewUser(User user) {
        if (userRepository.findUserByLogin(user.getLogin()) != null) {
            return false;
        }
        String passwordToEncode = user.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(passwordToEncode);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDto findUserByLogin(String login) {
        User userByLogin = userRepository.findUserByLogin(login);
        return userByLogin != null && !userByLogin.isDeleted() ? mapper.createUserDto(userByLogin) : null;
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.findById(id).ifPresent(user -> user.setDeleted(true));
    }

    @Override
    public List<UserDto> findAllNotDeletedUsers() {
        return userRepository
                .findAllNotDeletedUsers()
                .stream()
                .map(mapper::createUserDto)
                .sorted(Comparator.comparing(UserDto::getId))
                .toList();
    }

}
