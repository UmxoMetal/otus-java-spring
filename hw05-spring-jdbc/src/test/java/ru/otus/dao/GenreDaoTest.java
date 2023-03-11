package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.dao.impl.GenreDaoImpl;
import ru.otus.domain.GenreDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(GenreDaoImpl.class)
@DisplayName("Тестирование репозитория жанров")
public class GenreDaoTest {
    @Autowired
    private GenreDao genreDao;

    @Test
    @DisplayName("Создать жанр")
    void createBookTest() {
        var genreDto = GenreDto
                .builder()
                .genreName("Наименование жанра")
                .build();
        assertDoesNotThrow(() -> genreDao.createGenre(genreDto));
    }

    @Test
    @DisplayName("Выбрать жанр по айди")
    void getGenreByIdTest() {
        var genreByIdOpt = genreDao.getGenreById(1L);
        assertThat(genreByIdOpt).isNotEmpty();
        assertThat(genreByIdOpt.get()).hasFieldOrProperty("genreName");
    }

    @Test
    @DisplayName("Выбрать все жанры")
    void getAllGenresTest() {
        var allGenres = genreDao.getAllGenres();
        assertThat(allGenres).isNotEmpty();
    }

    @Test
    @DisplayName("Удалить жанр по айди")
    void deleteGenreById() {
        assertEquals(1, genreDao.deleteGenreById(1L));
    }
}