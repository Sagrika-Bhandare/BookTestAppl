package com.example.BookTestAppl.service;


import com.example.BookTestAppl.controller.Controller;
import com.example.BookTestAppl.entities.Book;
import com.example.BookTestAppl.exceptions.EmptyListException;
import com.example.BookTestAppl.exceptions.InvalidEntryException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.OrientationRequested;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class ServiceImplTest {
    Book book;
    @Autowired
    ServiceImpl service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ServiceInterface serviceInterface;

    @Autowired
    Controller controller;

    @BeforeEach
    public void init(){
        book=new Book();
        book.setIsbnNo(1234567891L);
        book.setBookName("Wings of Fire");
        book.setAuthorName("Kalam");
        book.setAuthorId(111);
        book.setYearOfPublication(2005);
    }
    @Order(1)
    @Test
    void getBookEmptyListTest(){
        //       serviceInterface.deleteBook(1234567890L);
        assertThrows(EmptyListException.class,()->serviceInterface.getBooks(),"No books present");
    }
    @Order(2)
    @Test
    void addBookTest() {
        Book actual= serviceInterface.addBook(book);
        Book expected=serviceInterface.getBookByIsbnNo(book.getIsbnNo());
        assertEquals(expected,actual);
        assertThrows(InvalidEntryException.class,()->serviceInterface.addBook(book),"Invalid entry");
    }
    @Order(3)
    @Test
    void addBookAlreadyPresentTest(){
        book.setIsbnNo(1234567892L);
        serviceInterface.addBook(book);
        assertThrows(InvalidEntryException.class,()->serviceInterface.addBook(book),"\"same book is already present \"");
    }
    @Order(4)
    @Test
    void getBookByIsbnNoTest() {
        book.setIsbnNo(8717483819L);
        serviceInterface.addBook(book);
        assertThrows(InvalidEntryException.class,()->serviceInterface.getBookByIsbnNo(1937388139L));

    }
    @Order(5)
    @Test
    void searchByAuthorTest() {
        book.setAuthorName("Kalam");
        book.setIsbnNo(1234567893L);
        serviceInterface.addBook(book);
        assertThrows(InvalidEntryException.class,()->serviceInterface.addBook(book),"no book are present in library with this author name");
    }
    @Order(6)
    @Test
    void updateBookTest() {
        book.setIsbnNo(8383981371L);
        book.setBookName("Wings of Fire");
        serviceInterface.addBook(book);
//        Book actual=serviceInterface.updateBook(book.getIsbnNo(),book);
//        Book expected =serviceInterface.getBookByIsbnNo(book.getIsbnNo());
       // assertEquals(expected,actual);
        assertThrows(InvalidEntryException.class,()->serviceInterface.getBookByIsbnNo(7536716371L));
    }
    @Order(7)
    @Test
    void deleteBookIfPresentTest() {
        assertThrows(InvalidEntryException.class,()->serviceInterface.deleteBook(12345678909L),"invalid entry");
        // assertEquals(expected,actual);
    }

    @ParameterizedTest
    @MethodSource("bookCollection")
    public void bookNotNull(){

        assertNotNull(book);
    }
    public static Stream<Book> bookCollection(){
        return Stream.of(new Book(1234567891L,"Wings of Fire",111, "Kalam",2005));
    }
    @ParameterizedTest
    @MethodSource("bookCollectionNull")
    public void bookNull(){
        Book actualBook=serviceInterface.getBookByIsbnNo(1535231456L);
        //Book book=new Book();
        Book books=new Book(1424521353L,"Atomic Habit",42,"PQR",2012);

//        assertTrue(StringUtils.isBlank(input));
        assertNull(null);
        assertThrows(NullPointerException.class,()->assertNull(null));
    }
    public static Stream<Book> bookCollectionNull(){

//       Stream.of(new Book());
//     return (Stream<Book>) assertThrows(NullPointerException.class,()->bookCollection());
//        Stream.of(null);
//        return (Stream<Book>) assertThrows(NullPointerException.class,()->bookCollectionNull());
       // return assertThrows(NullPointerException.class,()->bookCollectionNull(null));
        return null;
    }


    @ParameterizedTest
    @MethodSource("addBookMethod")
    public void addBookMethodSourceTest(Book book){
        assertThrows(InvalidEntryException.class,()->serviceInterface.addBook(book),"Invalid Entry");
    }
    public static Stream<Book> addBookMethod(){
        return Stream.of(new Book(123567891L,"Wings of Fire",111, "Kalam",2005));
    }

    @ParameterizedTest
    @MethodSource("deleteBookMethod")
    public void deleteBookMethodSourceTest(Book book){
        assertThrows(InvalidEntryException.class,()->serviceInterface.addBook(book),"Invalid Entry");
    }
    public static Stream<Book> deleteBookMethod(){
        return Stream.of(new Book(123567891L,"Wings of Fire",111, "Kalam",2005));
    }




}


