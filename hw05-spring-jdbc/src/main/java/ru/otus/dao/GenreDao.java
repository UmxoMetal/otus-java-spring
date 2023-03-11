package ru.otus.dao;

import ru.otus.domain.GenreDto;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    void createGenre(GenreDto genreDto);

    Optional<GenreDto> getGenreById(Long id);

    List<GenreDto> getAllGenres();

    List<GenreDto> getAllGenresByBooId(Long booId);

    int deleteGenreById(Long autId);
}