package ru.otus.dao;

import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void createBook(Book book);

    Optional<Book> getBookById(long booId);

    Optional<Book> getBookByIdWithGraph(long booId, String graph);

    List<Book> getAllBooks();

    List<Book> getAllBooksWithGraph(String graphName);

    int deleteBookById(long booId);
}