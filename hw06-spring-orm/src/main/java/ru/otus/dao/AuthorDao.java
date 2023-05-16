package ru.otus.dao;

import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> getById(long autId);

    List<Author> getAll();

    void delete(Author author);
}