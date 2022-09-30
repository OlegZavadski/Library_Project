package com.tms.service.impl;

import com.tms.model.User;
import com.tms.model.UserSecurity;
import com.tms.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User userByLogin = userService.findByLogin(login);
        if (userByLogin == null) {
            return new UserSecurity(null, null, null);
        }
        return new UserSecurity(userByLogin.getLogin(), userByLogin.getPassword(), userByLogin.getRole());
    }
}
