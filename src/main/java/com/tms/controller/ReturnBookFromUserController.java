package com.tms.controller;

import com.tms.model.Book;
import com.tms.model.BookAudit;
import com.tms.service.BookAuditService;
import com.tms.service.BookService;
import com.tms.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping(path = "/admin")
public class ReturnBookFromUserController extends AbstractAdminController {
    @Autowired
    private GeneralService generalService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookAuditService bookAuditService;

    @PostMapping(path = "/return_book_from_user")
    public String returnBookFromUser(@RequestParam Integer bookId,
                                     @RequestParam Integer userId,
                                     Model model) {
        Book bookById = bookService.findById(bookId);
        long dateOfIssue = bookById.getDateOfIssue().getTime();
        long dateOfReturn = new Date().getTime();
        long timeOfUse = dateOfReturn - dateOfIssue;
        Integer daysOfUse = (int) (timeOfUse / (24 * 60 * 60 * 1000));
        BookAudit bookAudit = new BookAudit(userId, bookById.getAuthor(), bookById.getTitle(), bookById.getYear(), bookById.getDateOfIssue(), new Date(), daysOfUse);
        bookAuditService.save(bookAudit);
        generalService.returnBookFromUser(bookId, userId);
        findOnlyActiveUsers(model);
        return "list-of-users-for-admin";
    }

}
