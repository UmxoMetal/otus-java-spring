package ru.otus.dao;

import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    void createGenre(Genre genre);

    Optional<Genre> getGenreById(Long genId);

    List<Genre> getAllGenres();

    int deleteGenreById(Long genId);
}