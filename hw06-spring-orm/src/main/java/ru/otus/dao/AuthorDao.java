package ru.otus.dao;

import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void createAuthor(Author author);

    Optional<Author> getAuthorById(Long autId);

    List<Author> getAllAuthors();

    int deleteAuthorById(Long autId);
}