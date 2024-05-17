package com.java.controller;

import com.java.exception.BookIDFound;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

import com.java.model.Book;
import com.java.exception.BookNotFound;

@RestController
public class bookController {
    private static List<Book> bookList = new ArrayList<>();
    static {
        Book book = new Book();
        book.setMaSach("1");
        book.setTenSach("Lap trinh");
        book.setTacGia("Long");
        book.setSoLuong(100);
        book.setGia(300000);
        bookList.add(book);
    }
    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public ResponseEntity<Object> createBook (@RequestBody Book book){
        boolean bookFound = false;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getMaSach().equals(book.getMaSach())){
                bookFound = true;
                break;
            }
        }
        if (bookFound) {
            throw new BookIDFound();
        }
        bookList.add(book);
        return new ResponseEntity<>("Da them", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/books")
    public ResponseEntity<Object> getBooks() {
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }
    @PutMapping("books/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable("id") String id, @RequestBody Book book) {
        boolean bookFound = false;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getMaSach().equals(id)) {
                bookList.remove(i);
                book.setMaSach(id);
                bookList.add(i, book);
                bookFound = true;
                break;
            }
        }
        if (!bookFound) {
            throw new BookNotFound();
        }
        return new ResponseEntity<>("Thanh cong", HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteBook/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable("id")String id) {
        boolean bookFound = false;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getMaSach().equals(id)){
                bookList.remove(i);
                bookFound = true;
                break;
            }
        }
        if (!bookFound) {
            throw new BookNotFound();
        }
        return new ResponseEntity<>("Da Xoa",HttpStatus.OK);
    }
}
