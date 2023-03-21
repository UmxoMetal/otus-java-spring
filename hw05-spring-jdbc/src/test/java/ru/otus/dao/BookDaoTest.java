package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.dao.impl.BookDaoImpl;
import ru.otus.domain.BookDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(BookDaoImpl.class)
@DisplayName("Тестирование репозитория книг")
public class BookDaoTest {
    @Autowired
    private BookDao bookDao;

    @Test
    @DisplayName("Создать книгу")
    void createBookTest() {
        var bookDto = BookDto
                .builder()
                .bookName("Наименование книги")
                .build();
        assertDoesNotThrow(() -> bookDao.createBook(bookDto));
    }

    @Test
    @DisplayName("Выбрать книгу по айди")
    void getBookByIdTest() {
        var bookByIdOpt = bookDao.getBookById(1L);
        assertThat(bookByIdOpt).isNotEmpty();
        assertThat(bookByIdOpt.get()).hasFieldOrProperty("bookName");
    }

    @Test
    @DisplayName("Выбрать все книги")
    void getAllBooksTest() {
        var allBooks = bookDao.getAllBooks();
        assertThat(allBooks).isNotEmpty();
    }

    @Test
    @DisplayName("Удалить книгу по айди")
    void deleteBookById() {
        assertEquals(1, bookDao.deleteBookById(1L));
    }
}