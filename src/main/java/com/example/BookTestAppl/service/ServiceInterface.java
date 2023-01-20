package com.example.BookTestAppl.service;

import com.example.BookTestAppl.entities.Book;

import java.util.ArrayList;
import java.util.List;

public interface ServiceInterface {

    List<Book> getBooks();
    Book addBook(Book book);
    Book getBookByIsbnNo(long isbnNo);
    List<Book> searchBookByAuthor(String authorName);
    Book updateBook(long isbnNo,Book book);
    String deleteBook(long isbnNo);
}
