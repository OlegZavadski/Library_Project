package com.tms.controller;

import com.tms.model.Book;
import com.tms.model.BookAudit;
import com.tms.service.BookAuditService;
import com.tms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Value(value = "${size-of-page}")
    private int sizeOfPage;
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
    public String getUsageHistory(@RequestParam(defaultValue = "0") Integer page,
                                  Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = ((UserDetails) principal).getUsername();
        Integer userId = userService.findUserByLogin(login).getId();
        Page<BookAudit> pages = bookAuditService.findBookAuditByUserId(userId, PageRequest.of(page, sizeOfPage));
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("booksAudit", pages.getContent());
        return "usage-history-for-user";
    }

}
