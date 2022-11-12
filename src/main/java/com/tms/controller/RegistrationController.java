package com.tms.controller;

import com.tms.model.ROLE;
import com.tms.model.User;
import com.tms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/admin")
public class RegistrationController extends AbstractAdminController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String registration(@RequestParam String login,
                               @RequestParam String password,
                               Model model) {
        User userForSave = new User(login, password, ROLE.ROLE_USER);
        if (login.isBlank() || password.isBlank()) {
            model.addAttribute("error", "Some field is empty");
            return "registration";
        }
        if (!userService.saveNewUser(userForSave)) {
            model.addAttribute("error", "User with this login exists!");
            return "registration";
        }
        userService.saveNewUser(userForSave);
        findOnlyActiveUsers(model);
        return "list-of-users-for-admin";
    }

}