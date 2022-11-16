package com.tms.service.impl;

import com.tms.model.User;
import com.tms.service.UserValidator;
import org.springframework.stereotype.Service;

@Service
public class UserValidatorImpl implements UserValidator {
    @Override
    public boolean isValidUserForSave(User user) {
        return !user.getLogin().isBlank() && !user.getPassword().isBlank();
    }
}
