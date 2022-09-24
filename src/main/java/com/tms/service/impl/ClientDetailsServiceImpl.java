package com.tms.service.impl;

import com.tms.model.Client;
import com.tms.model.ClientSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsServiceImpl implements UserDetailsService {
    private final ClientServiceImpl clientService;

    public ClientDetailsServiceImpl(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client clientByLogin = clientService.findByLogin(username);
        return new ClientSecurity(clientByLogin.getLogin(), clientByLogin.getPassword(), clientByLogin.getRole());
    }
}
