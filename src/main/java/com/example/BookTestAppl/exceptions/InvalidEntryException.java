package com.example.BookTestAppl.exceptions;

public class InvalidEntryException extends RuntimeException{
    public InvalidEntryException(String msg){
        super(msg);
    }
}
