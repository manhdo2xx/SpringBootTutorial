package com.java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
    public class BookNotFound extends RuntimeException{
        @ExceptionHandler(value = BookNotFound.class)
        public ResponseEntity<Object> exception(BookNotFound exception) {
            return new ResponseEntity<>("Khong ton tai quyen sach nay!", HttpStatus.NOT_FOUND);
        }
    }

