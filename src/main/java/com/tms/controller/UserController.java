package com.tms.controller;

import com.tms.model.Book;
import com.tms.service.BookAuditService;
import com.tms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookAuditService bookAuditService;

    @GetMapping
    public String main(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = ((UserDetails) principal).getUsername();
        List<Book> booksOfUser = userService
                .findUserByLogin(login)
                .getBooks()
                .stream()
                .sorted(Comparator.comparing(Book::getDateOfIssue))
                .toList();
        model.addAttribute("books", booksOfUser);
        return "user";
    }

    @GetMapping(path = "/usage_history")
    public String getUsageHistory(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = ((UserDetails) principal).getUsername();
        Integer userId = userService.findUserByLogin(login).getId();
        model.addAttribute("books_audit", bookAuditService.findBookAuditByUserId(userId));
        return "usage-history-for-user";
    }

}
