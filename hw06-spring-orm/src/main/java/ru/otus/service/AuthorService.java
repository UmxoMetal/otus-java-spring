package ru.otus.service;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorService {
    void createAuthor(Author author);

    Author getAuthorById(long autId);

    List<Author> getAllAuthors();

    void deleteAuthorById(long autId);
}