package com.example.BookTestAppl.controller;

import com.example.BookTestAppl.entities.Book;
import com.example.BookTestAppl.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    ServiceInterface serviceInterface;

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Book> getBooks(){

        return this.serviceInterface.getBooks();
    }
    @GetMapping("/books/getBookByIsbnNo/{isbnNo}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookByIsbnNo(@PathVariable long isbnNo){
        return this.serviceInterface.getBookByIsbnNo(isbnNo);
    }
    @GetMapping("/books/getByAuthor/{author}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> searchBookByAuthor(@PathVariable String author){
        return this.serviceInterface.searchBookByAuthor(author);
    }

    @PostMapping("/books/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book){
        return this.serviceInterface.addBook(book);
    }

    @PutMapping("/books/update/{isbnNo}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable long isbnNo,@RequestBody Book book){
        return this.serviceInterface.updateBook(isbnNo,book);
    }
    @DeleteMapping("/books/deleteBookByIsbnNo/{isbnNo}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteBook(@PathVariable long isbnNo){
        return this.serviceInterface.deleteBook(isbnNo);
    }
}
