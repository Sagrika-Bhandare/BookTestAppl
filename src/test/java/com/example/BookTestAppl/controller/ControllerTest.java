package com.example.BookTestAppl.controller;

import com.example.BookTestAppl.entities.Book;
import com.example.BookTestAppl.service.ServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class ControllerTest {

    Book book;
    @Autowired
    ServiceInterface serviceInterface;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void init(){
        book=new Book();
        book.setIsbnNo(1234567891L);
        book.setBookName("Wings of Fire");
        book.setAuthorId(111);
        book.setAuthorName("A.P.J Kalam");
        book.setYearOfPublication(2005);
        serviceInterface.addBook(book);
    }
    @Order(4)
    @Test
    void getAllBooks() throws Exception {
        ObjectMapper mapper=new ObjectMapper();

        this.mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(book))).andExpect(status().isAccepted());
    }

    @Order(2)
    @Test
    void getBookByIsbnNoTestController() throws Exception {
        book.setIsbnNo(1234567891L);
       serviceInterface.addBook(book);
        this.mockMvc.perform(get("/books/getBookByIsbnNo/1234567891")
                .contentType(MediaType.APPLICATION_JSON).content(String.valueOf(1234567891L)))
                .andExpect(status().isOk());
    }
    @Order(3)
    @Test
    void searchBookByAuthorTestController() throws Exception {
        ObjectMapper mapper=new ObjectMapper();
        this.mockMvc.perform(get("/books/getByAuthor/A.P.J Kalam")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(book))).andExpect(status().isOk());
    }

    @Order(1)
    @Test
    void addBookTestController() throws Exception{
        ObjectMapper mapper=new ObjectMapper();
        book.setIsbnNo(8747839485L);
        this.mockMvc.perform(post("/books/add")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(book)))
                .andExpect(status().isCreated());

    }

@Order(5)
    @Test
    void updateBookTestController() throws Exception{
        ObjectMapper mapper=new ObjectMapper();
        this.mockMvc.perform(put("/books/update/1234567891")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(book))).andExpect(status().isCreated());
    }
    @Order(6)
    @Test
    void deleteBookTestController() throws Exception {
        ObjectMapper mapper=new ObjectMapper();

        this.mockMvc.perform(delete("/books/deleteBookByIsbnNo/1234567891")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(book)))
                .andExpect(status().isAccepted());
    }

    @Test
    void getBooks() {
    }

    @Test
    void getBookByIsbnNo() {
    }

    @Test
    void searchBookByAuthor() {
    }

    @Test
    void addBook() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }
}