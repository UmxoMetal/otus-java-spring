package ru.otus.service;

import ru.otus.domain.GenreDto;

import java.util.List;

public interface GenreService {
    void createGenre(GenreDto genreDto);

    GenreDto getGenreById(Long genId);

    List<GenreDto> getAllGenres();

    void deleteGenreById(Long autId);
}