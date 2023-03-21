package ru.otus.dao;

import ru.otus.domain.AuthorDto;
import ru.otus.domain.BookToAuthorDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AuthorDao {
    void createAuthor(AuthorDto authorDto);

    Optional<AuthorDto> getAuthorById(Long autId);

    List<AuthorDto> getAllAuthors();

    List<AuthorDto> getAllAuthorsByBooId(Long booId);

    List<BookToAuthorDto> getAllAuthorsWithBooId();

    int deleteAuthorById(Long autId);
}