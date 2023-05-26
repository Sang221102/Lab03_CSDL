package com.example.demo.controller;


import com.example.demo.Service.BookService;
import com.example.demo.Service.CategoryService;
import com.example.demo.entity.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/books")
public class Bookcontroller {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books",books);
        return "book/list";
    }
    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBooks(@Valid @ModelAttribute("book") Book bookInput, BindingResult bindingResult, Model model){
        if (bindingResult != null && bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList();
            model.addAttribute("errors", errors);
            return "book/add";
        }
        bookInput.setId(bookService.getAllBooks().stream().count()+1);
        bookService.addBook(bookInput);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book book) {
        bookService.updateBook(book);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBooks(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }


}
