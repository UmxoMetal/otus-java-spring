package ru.otus.service;

import ru.otus.domain.Book;

import java.util.List;

public interface BookService {
    void createBook(Book book);

    Book getBookById(long booId);

    Book getBookByIdWithGraph(long booId, String graph);

    Book getBookByIdWithFullInfo(long booId);

    List<Book> getAllBooks();

    List<Book> getAllBooksWithInfo();

    void deleteBookById(long booId);
}