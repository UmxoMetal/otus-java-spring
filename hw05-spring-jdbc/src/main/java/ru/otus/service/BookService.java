package ru.otus.service;

import ru.otus.domain.BookDto;

import java.util.List;

public interface BookService {
    void createBook(BookDto bookDto);

    BookDto getBookById(Long booId);

    BookDto getBookByIdWithFullInfo(Long booId);

    List<BookDto> getAllBooks();

    List<BookDto> getAllBooksWithFullInfo();

    void deleteBookById(Long autId);
}