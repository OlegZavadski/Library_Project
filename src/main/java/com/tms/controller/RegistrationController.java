package com.tms.controller;

import com.tms.model.ROLE;
import com.tms.model.User;
import com.tms.service.BookService;
import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/admin")
public class RegistrationController extends AbstractAdminController {

    public RegistrationController(UserService userService, BookService bookService, GeneralService generalService) {
        super(userService, bookService, generalService);
    }

    @GetMapping(path = "/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String registration(@RequestParam String login,
                               @RequestParam String password,
                               Model model) {
        if (login.isBlank() || password.isBlank()) {
            model.addAttribute("error", "Some field is empty");
            return "registration";
        }
        if (userService.findByLogin(login) != null) {
            model.addAttribute("error", "User with this login exists!");
            return "registration";
        }
        userService.saveNewUser(new User(login, password, ROLE.ROLE_USER));
        findOnlyActiveUsers(model);
        return "admin";
    }

}