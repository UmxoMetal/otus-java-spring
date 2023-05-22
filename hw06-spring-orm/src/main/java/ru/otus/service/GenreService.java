package ru.otus.service;

import ru.otus.domain.Genre;

import java.util.List;

public interface GenreService {
    void createGenre(Genre genreDto);

    Genre getGenreById(long genId);

    List<Genre> getAllGenres();

    void deleteGenreById(long autId);
}