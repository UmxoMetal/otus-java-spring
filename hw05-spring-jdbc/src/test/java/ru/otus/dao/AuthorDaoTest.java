package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.dao.impl.AuthorDaoImpl;
import ru.otus.domain.AuthorDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(AuthorDaoImpl.class)
@DisplayName("Тестирование репозитория авторов")
public class AuthorDaoTest {
    @Autowired
    private AuthorDao authorDao;

    @Test
    @DisplayName("Создать автора")
    void createAuthorTest() {
        var authorDto = AuthorDto
                .builder()
                .fio("Фио автора")
                .build();
        assertDoesNotThrow(() -> authorDao.createAuthor(authorDto));
    }

    @Test
    @DisplayName("Выбрать книгу по айди")
    void getAuthorByIdTest() {
        var authorDtoOpt = authorDao.getAuthorById(1L);
        assertThat(authorDtoOpt).isNotEmpty();
        assertThat(authorDtoOpt.get()).hasFieldOrProperty("fio");
    }

    @Test
    @DisplayName("Выбрать всех авторов")
    void getAllAuthorsTest() {
        var allAuthors = authorDao.getAllAuthors();
        assertThat(allAuthors).isNotEmpty();
    }

    @Test
    @DisplayName("Выбрать всех авторов c айди книг")
    void getAllAuthorsWithBooIdTest() {
        var allAuthorsWithBooId = authorDao.getAllAuthorsWithBooId();
        assertThat(allAuthorsWithBooId).isNotEmpty();
    }

    @Test
    @DisplayName("Удалить книгу по айди")
    void deleteAuthorById() {
        assertEquals(1, authorDao.deleteAuthorById(1L));
    }
}