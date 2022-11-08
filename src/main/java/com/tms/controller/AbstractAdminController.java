package com.tms.controller;

import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public abstract class AbstractAdminController {
    protected static final Integer SIZE_OF_PAGE = 10;
    protected final UserService userService;

    public AbstractAdminController(UserService userService) {
        this.userService = userService;
    }

    protected void findOnlyActiveUsers(Model model) {
        model.addAttribute("allUsers", userService.findAllNotDeletedUsers());
    }

}
