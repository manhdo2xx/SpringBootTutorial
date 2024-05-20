package com.java.service;

import com.java.model.Book;

import java.util.Collection;

public interface BookService {
    public abstract void createBook(Book book);

    public abstract void updateBook(String id, Book book);

    public abstract void deleteBook(String id);

    public abstract Collection<Book> getBook();
}
