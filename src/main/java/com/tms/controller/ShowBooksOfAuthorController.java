package com.tms.controller;

import com.tms.model.BookProjection;
import com.tms.service.BookService;
import com.tms.service.GeneralService;
import com.tms.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/")
public class ShowBooksOfAuthorController extends AbstractAdminController {

    public ShowBooksOfAuthorController(UserService userService, BookService bookService, GeneralService generalService) {
        super(userService, bookService, generalService);
    }

    @GetMapping(path = "/authors/{author}")
    public String showBooksOfUser(@PathVariable(name = "author") String author,
                                  @RequestParam(defaultValue = "0") Integer page,
                                  Model model) {
        Page<BookProjection> pages = bookService.findBooksByAuthor(author, PageRequest.of(page, SIZE_OF_PAGE));
        model.addAttribute("author", author);
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("booksOfAuthor", pages.getContent());
        return "books-of-author";
    }

}
