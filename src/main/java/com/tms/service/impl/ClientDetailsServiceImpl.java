package com.tms.service.impl;

import com.tms.model.Client;
import com.tms.model.ClientSecurity;
import com.tms.service.ClientService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsServiceImpl implements UserDetailsService {
    private final ClientService clientService;

    public ClientDetailsServiceImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client clientByLogin = clientService.findByLogin(login);
        if (clientByLogin == null) {
            return new ClientSecurity(null, null, null);
        }
        return new ClientSecurity(clientByLogin.getLogin(), clientByLogin.getPassword(), clientByLogin.getRole());
    }
}
