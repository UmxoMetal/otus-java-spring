package otus.service;

import otus.domain.Genre;

import java.util.List;

public interface GenreService {
    void createGenre(Genre genre);

    Genre getGenreById(long genId);

    List<Genre> getAllGenres();

    void deleteGenreById(long autId);
}