package com.otus.homework.homework8_mongo.service;

import com.otus.homework.homework8_mongo.domain.Author;
import com.otus.homework.homework8_mongo.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAll();
    Optional<Book> getById(String id);
    List<Book> getByTitle(String name);
    List<Book> getByAuthor(Author author);
    void delete(String id);

}
