package com.example.BookTestAppl.service;

import com.example.BookTestAppl.controller.Controller;
import com.example.BookTestAppl.entities.Book;
import com.example.BookTestAppl.exceptions.EmptyListException;
import com.example.BookTestAppl.exceptions.InvalidEntryException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;


@Service
public class ServiceImpl implements ServiceInterface{

    Logger logger= LoggerFactory.getLogger(Controller.class);
    List<Book> bookList=new ArrayList<>();
    @Override
    public Book addBook(Book book) {
        for(Book bookObj:bookList){
            if(bookObj.getIsbnNo()==book.getIsbnNo()){
                throw new InvalidEntryException("Book is already existing,cannot add");
            }
        }
        String isbnLength=String.valueOf(book.getIsbnNo());
        if(isbnLength.length()!=10)
            throw new InvalidEntryException("IsbnNo should of 10 digits");
        if(!(book.getBookName().length()>4) &&(book.getBookName()!=null))
            throw new InvalidEntryException("Book name length should be greater than 4 and should not be null");
        if(!(book.getYearOfPublication()>2000))
            throw new InvalidEntryException("The book published after 2000 should only be accepted");
        else
            bookList.add(book);
        logger.info("Book added successfully "+book);
        return book;
    }
    @Override
    public List<Book> getBooks() {
        logger.info("getting the books");
        if(bookList.isEmpty()){
            throw new EmptyListException("No books are present");
        }
        return bookList;
    }
    @Override
    public Book getBookByIsbnNo(long isbnNo) {
        Book book=null;
        for(Book bookObj:bookList){
            if(bookObj.getIsbnNo()==isbnNo){
                book=bookObj;
                break;
            }
        }
        if(book==null){
            throw new InvalidEntryException("Invalid IsbnNo: "+isbnNo);
        }
        logger.info("Book with isbnNo");
        return book;
    }

    @Override
    public List<Book> searchBookByAuthor(String authorName) {

        List<Book> bookListAuthor=new ArrayList<>();
        for(Book bookAuthor:bookList){
            if(bookAuthor.getAuthorName().equals(authorName))
                bookListAuthor.add(bookAuthor);
        }
        if(bookListAuthor.isEmpty())
            throw new InvalidEntryException("Author is not available for the given name");

        logger.info("Books for the given author name");
        return bookListAuthor;
    }

    @Override
    public Book updateBook(long isbnNo, Book book) {

        ServiceImpl service=new ServiceImpl();
        for(Book bookObj:bookList){
            if(book.getIsbnNo()==isbnNo){
                bookObj.setIsbnNo(isbnNo);
                bookObj.setBookName(book.getBookName());
                bookObj.setAuthorId(book.getAuthorId());
                bookObj.setAuthorName(book.getAuthorName());
                bookObj.setYearOfPublication(book.getYearOfPublication());
                service.addBook(bookObj);
                break;
            }
        }
        logger.info("Successfully updated the existing entry");

        return book;
    }

    @Override
    public String deleteBook(long isbnNo) {
        Book book=null;
        for(Book bookObj:bookList){
            if(bookObj.getIsbnNo()==isbnNo){
                book=bookObj;
                bookList.remove(bookObj);
                break;
            }
        }
        if(book==null)
            throw new InvalidEntryException("Book is not present with the given isbnNo or Invalid isbnNo");
        logger.info("Book deleted successfully");
        return "deleted";
    }
}
