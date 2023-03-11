package ru.otus.dao;

import ru.otus.domain.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void createAuthor(AuthorDto authorDto);

    Optional<AuthorDto> getAuthorById(Long autId);

    List<AuthorDto> getAllAuthors();

    List<AuthorDto> getAllAuthorsByBooId(Long booId);

    int deleteAuthorById(Long autId);
}