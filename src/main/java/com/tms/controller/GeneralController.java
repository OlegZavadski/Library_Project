package com.tms.controller;

import com.tms.service.BookService;
import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/")
public class GeneralController extends AbstractAdminController {

    public GeneralController(UserService userService, BookService bookService, GeneralService generalService) {
        super(userService, bookService, generalService);
    }

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("books", bookService.findAllBooksWithCount());
        return "main-page";
    }

    @GetMapping(path = "search")
    public String search(@RequestParam String forSearch,
                         Model model) {
        model.addAttribute("books", bookService.getBooksFromSearch(forSearch));
        return "main-page";
    }

}
