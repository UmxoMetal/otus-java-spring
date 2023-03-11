package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.GenreDao;
import ru.otus.domain.GenreDto;
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
    private static final String MSG_DELETION_FAILED = "Genre deletion by id failed";
    private static final String MSG_BLANK_GENRE_NAME = "Genre name should not be blank";

    @Override
    public void createGenre(GenreDto genreDto) {
        genreDao.createGenre(genreDto);
    }

    @Override
    public GenreDto getGenreById(Long genId) {
        return genreDao.getGenreById(genId)
                .orElseThrow(() -> new BookServiceException(format(MSG_GENRE_NOT_FOUND, genId)));
    }

    @Override
    public List<GenreDto> getAllGenres() {
        var allGenres = genreDao.getAllGenres();
        if (isEmpty(allGenres)) {
            throw new BookServiceException(MSG_EMPTY_GENRE_TABLE);
        }
        return allGenres;
    }

    @Override
    public void deleteGenreById(Long autId) {
        if (genreDao.deleteGenreById(autId) == 0) {
            throw new BookServiceException(MSG_DELETION_FAILED);
        }
    }
}