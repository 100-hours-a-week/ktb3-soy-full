package com.example.spring_practice.controller;

import com.example.spring_practice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {
    public final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public String getList(Model model){
        System.out.println(bookService.getAllBooks());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "/books/list";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Model model){
        model.addAttribute("book", bookService.getBookById(id));
        return "/books/detail";
    }
}
