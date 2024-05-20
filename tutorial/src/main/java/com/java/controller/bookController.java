package com.java.controller;

import com.java.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.java.model.Book;

@RestController
public class bookController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/books")
    public ResponseEntity<Object> getBooks() {
        return new ResponseEntity<>(bookService.getBook(), HttpStatus.OK);
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public ResponseEntity<Object> createBook(@RequestBody Book book) {
        bookService.createBook(book);
        return new ResponseEntity<>("Đã thêm!", HttpStatus.CREATED);
    }

    @PutMapping("books/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable("id") String id, @RequestBody Book book) {
        bookService.updateBook(id, book);
        return new ResponseEntity<>("Đã sửa!", HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteBook/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable("id") String id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Đã xóa!", HttpStatus.OK);
    }
}
