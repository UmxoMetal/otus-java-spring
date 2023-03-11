package ru.otus.dao;

import ru.otus.domain.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void createBook(BookDto bookDto);

    Optional<BookDto> getBookById(Long booId);

    List<BookDto> getAllBooks();

    int deleteBookById(Long autId);
}