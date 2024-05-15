package com.java.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import com.java.model.Book;
import com.java.exception.BookNotFound;

@RestController
public class bookController {
    private static Map<String , Book> bookMap = new HashMap<>();
    static {
        Book book = new Book();
        book.setMaSach("1");
        book.setTenSach("Lap trinh");
        book.setTacGia("Long");
        book.setSoLuong(100);
        book.setGia(300000);
        bookMap.put(book.getMaSach(),book);

    }
    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public ResponseEntity<Object> createBook (@RequestBody Book book){

        bookMap.put(book.getMaSach(),book);
        return new ResponseEntity<>("Da them", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/books")
    public ResponseEntity<Object> getBooks() {
        return new ResponseEntity<>(bookMap.values(), HttpStatus.OK);
    }
    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public  ResponseEntity<Object> updateBook(@PathVariable("id") String id, @RequestBody Book book){
        if (!bookMap.containsKey(id)) throw new BookNotFound();
        bookMap.remove(id);
        book.setMaSach(id);
        bookMap.put(id,book);
        return new ResponseEntity<>("Thanh cong",HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteBook/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable("id")String id){
        if (!bookMap.containsKey(id)) throw new BookNotFound();
        bookMap.remove(id);
        return new ResponseEntity<>("Da xoa",HttpStatus.OK);
    }
}
