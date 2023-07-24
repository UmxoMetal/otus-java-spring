package otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.domain.Genre;
import otus.exception.BookServiceException;
import otus.repository.GenreRepository;
import otus.service.GenreService;

import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private static final String MSG_GENRE_NOT_FOUND = "Genre with id %s not found";
    private static final String MSG_EMPTY_GENRE_TABLE = "Genre table is empty";

    @Override
    public void createGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public Genre getGenreById(long genId) {
        return genreRepository.findById(genId)
                .orElseThrow(() -> new BookServiceException(format(MSG_GENRE_NOT_FOUND, genId)));
    }

    @Override
    public List<Genre> getAllGenres() {
        var allGenres = genreRepository.findAll();
        if (isEmpty(allGenres)) {
            throw new BookServiceException(MSG_EMPTY_GENRE_TABLE);
        }
        return allGenres;
    }

    @Override
    public void deleteGenreById(long genId) {
        genreRepository.deleteById(genId);
    }
}