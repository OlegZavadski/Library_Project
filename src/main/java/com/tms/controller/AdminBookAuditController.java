package com.tms.controller;

import com.tms.model.BookAudit;
import com.tms.service.BookAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/admin")
public class AdminBookAuditController extends AbstractAdminController {
    @Autowired
    private BookAuditService bookAuditService;

    @GetMapping(path = "/show_issue_history")
    public String showAllBooksAudit(@RequestParam(defaultValue = "0") Integer page,
                                    Model model) {
        Page<BookAudit> pages = bookAuditService.findAllBookAudit(PageRequest.of(page, SIZE_OF_PAGE));
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("books_audit", pages.getContent());
        return "issue-history-for-admin";
    }

}
