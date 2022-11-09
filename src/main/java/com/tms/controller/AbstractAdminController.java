package com.tms.controller;

import com.tms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public abstract class AbstractAdminController {
    protected static final Integer SIZE_OF_PAGE = 10;
    @Autowired
    private UserService userService;

    protected void findOnlyActiveUsers(Model model) {
        model.addAttribute("allUsers", userService.findAllNotDeletedUsers());
    }

}
