package com.tms.service;

import com.tms.model.User;

public interface UserValidator {

    boolean isValidUserForSave(User user);

}
