package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Genre;
import ru.otus.exception.BookServiceException;
import ru.otus.service.GenreService;
import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    private static final String MSG_GENRE_NOT_FOUND = "Genre with id %s not found";
    private static final String MSG_EMPTY_GENRE_TABLE = "Genre table is empty";

    @Override
    @Transactional
    public void createGenre(Genre genreDto) {
        genreDao.create(genreDto);
    }

    @Override
    public Genre getGenreById(long genId) {
        return genreDao.getById(genId)
                .orElseThrow(() -> new BookServiceException(format(MSG_GENRE_NOT_FOUND, genId)));
    }

    @Override
    public List<Genre> getAllGenres() {
        var allGenres = genreDao.getAll();
        if (isEmpty(allGenres)) {
            throw new BookServiceException(MSG_EMPTY_GENRE_TABLE);
        }
        return allGenres;
    }

    @Override
    @Transactional
    public void deleteGenreById(long genId) {
        genreDao.deleteById(getGenreById(genId));
    }
}