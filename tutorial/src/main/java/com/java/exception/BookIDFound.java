package com.java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookIDFound extends RuntimeException{
    @ExceptionHandler(value = BookIDFound.class)
    public ResponseEntity<Object> exception (BookIDFound exception) {
        return new ResponseEntity<>("Trung id!", HttpStatus.FOUND);
    }
}

