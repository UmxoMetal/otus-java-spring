package ru.otus.dao;

import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    void create(Genre genre);

    Optional<Genre> getById(long genId);

    List<Genre> getAll();

    int deleteById(long genId);
}