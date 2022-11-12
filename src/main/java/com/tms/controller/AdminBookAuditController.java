package com.tms.controller;

import com.tms.service.BookAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin")
public class AdminBookAuditController extends AbstractAdminController {
    @Autowired
    private BookAuditService bookAuditService;

    @GetMapping(path = "/show_issue_history")
    public String showAllBooksAudit(Model model) {
        model.addAttribute("books_audit", bookAuditService.findAllBookAudit());
        return "issue-history-for-admin";
    }

}
