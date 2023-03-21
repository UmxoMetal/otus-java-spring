package ru.otus.dao;

import ru.otus.domain.BookDto;
import ru.otus.domain.BookToGenreAndAuthorDto;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void createBook(BookDto bookDto);

    Optional<BookDto> getBookById(Long booId);

    List<BookToGenreAndAuthorDto> getBookByIdWithFullInfo(Long booId);

    List<BookDto> getAllBooks();

    int deleteBookById(Long autId);
}