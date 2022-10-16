package com.tms.service.impl;

import com.tms.model.User;
import com.tms.repository.UserRepository;
import com.tms.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void saveNewUser(User user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public List<User> findOnlyUsers() {
        return userRepository.findOnlyUsers()
                .stream()
                .sorted(Comparator.comparing(User::getId))
                .toList();
    }
}
