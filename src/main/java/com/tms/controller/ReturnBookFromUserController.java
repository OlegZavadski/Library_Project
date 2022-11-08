package com.tms.controller;

import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/admin")
public class ReturnBookFromUserController extends AbstractAdminController {

    private final GeneralService generalService;

    public ReturnBookFromUserController(UserService userService, GeneralService generalService) {
        super(userService);
        this.generalService = generalService;
    }

    @PostMapping(path = "/return_book_from_user")
    public String returnBookFromUser(@RequestParam Integer bookId,
                                     @RequestParam Integer userId,
                                     Model model) {
        generalService.returnBookFromUser(bookId, userId);
        findOnlyActiveUsers(model);
        return "list-of-users-for-admin";
    }

}
