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
    public UserDto findById(Integer id) {
        User userFromDb = userRepository.findById(id).orElse(null);
        return userFromDb != null ? mapper.createUserDto(userFromDb) : null;
    }

    @Override
    public void saveNewUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDto findByLogin(String login) {
        User userByLogin = userRepository.findByLogin(login);
        return userByLogin != null && !userByLogin.isDeleted() ? mapper.createUserDto(userByLogin) : null;
    }

    @Override
    public void delete(Integer id) {
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
