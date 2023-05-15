package ru.otus.dao;

import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> getById(long booId);

    Optional<Book> getByIdWithGraph(long booId, String graphName);

    List<Book> getAll();

    List<Book> getAllWithGraph(String graphName);

    int deleteById(long booId);
}