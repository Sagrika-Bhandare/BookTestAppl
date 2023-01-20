package com.example.BookTestAppl.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(value=InvalidEntryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ErrorResponse InvalidEntryException(InvalidEntryException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(),e.getMessage());
    }

    @ExceptionHandler(value=EmptyListException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse EmptyListException(EmptyListException e){
        return new ErrorResponse((HttpStatus.BAD_REQUEST.value()),e.getMessage());
    }
}
