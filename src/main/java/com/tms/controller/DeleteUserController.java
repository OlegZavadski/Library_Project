package com.tms.controller;

import com.tms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/admin")
public class DeleteUserController extends AbstractAdminController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/delete_user")
    public String delete(@RequestParam Integer userId,
                         Model model) {
        userService.deleteUserById(userId);
        findOnlyActiveUsers(model);
        return "list-of-users-for-admin";
    }

}
