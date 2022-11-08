package com.tms.controller;

import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/admin")
public class DeleteUserController extends AbstractAdminController {
    private final UserService userService;

    public DeleteUserController(UserService userService, UserService userService1) {
        super(userService);
        this.userService = userService1;
    }

    @PostMapping(path = "/delete_user")
    public String delete(@RequestParam Integer userId,
                         Model model) {
        userService.deleteUserById(userId);
        findOnlyActiveUsers(model);
        return "list-of-users-for-admin";
    }

}
