package com.java.service;

import com.java.exception.BookIDFound;
import com.java.exception.BookNotFound;
import com.java.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
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

    @Override
    public void createBook(Book book) {
        boolean bookFound = false;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getMaSach().equals(book.getMaSach())) {
                bookFound = true;
                break;
            }
        }
        if (bookFound) {
            throw new BookIDFound();
        }
        bookList.add(book);
    }

    @Override
    public void updateBook(String id, Book book) {
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
    }

    @Override
    public void deleteBook(String id) {
        boolean bookFound = false;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getMaSach().equals(id)) {
                bookList.remove(i);
                bookFound = true;
                break;
            }
        }
        if (!bookFound) {
            throw new BookNotFound();
        }
    }

    @Override
    public Collection<Book> getBook() {
        return new ArrayList<>(bookList);
    }
}
