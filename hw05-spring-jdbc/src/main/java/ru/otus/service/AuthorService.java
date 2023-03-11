package ru.otus.service;

import ru.otus.domain.AuthorDto;

import java.util.List;

public interface AuthorService {
    void createAuthor(AuthorDto authorDto);

    AuthorDto getAuthorById(Long id);

    List<AuthorDto> getAllAuthors();

    void deleteAuthorById(Long autId);
}