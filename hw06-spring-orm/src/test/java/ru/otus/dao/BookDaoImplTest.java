package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.impl.BookDaoImpl;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.List;

import static java.util.Collections.*;
import static org.apache.commons.collections4.CollectionUtils.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static ru.otus.dao.impl.BookDaoImpl.BOOK_AUTHORS_GRAPH;

@DataJpaTest
@Import(BookDaoImpl.class)
@DisplayName("Класс для тестирования репозитория по работе с книгами")
class BookDaoImplTest {

    @Autowired
    private BookDaoImpl bookDao;

    @Test
    @DisplayName("Получение всех книг")
    void getAllBooksTest() {
        var allBooks = bookDao.getAll();
        assertTrue(isNotEmpty(allBooks));
    }

    @Test
    @DisplayName("Получение всех книг c графом")
    void getAllBooksWithGraphTest() {
        var allBooksWithGraph = bookDao.getAllWithGraph(BOOK_AUTHORS_GRAPH);
        assertTrue(isNotEmpty(allBooksWithGraph));
    }

    @Test
    @DisplayName("Получение книги по айди. Книга найдена")
    void getBookByIdFoundTest() {
        var bookByIdOpt = bookDao.getById(3L);
        assertTrue(bookByIdOpt.isPresent());

        assertThat(bookByIdOpt.get())
               .isNotNull()
               .usingRecursiveComparison()
               .isEqualTo(Book.builder()
                       .booId(3L)
                       .bookName("Spring in Action")
                       .bookAuthors(emptyList())
                       .bookGenres(emptyList())
                       .bookComments(emptyList())
                       .build());
    }

    @Test
    @DisplayName("Получение книги по айди. Книга не найдена")
    void getBookByIdNotFoundTest() {
        var notFoundBookByIdOpt = bookDao.getById(4L);
        assertTrue(notFoundBookByIdOpt.isEmpty());
    }

    @Test
    @DisplayName("Получение книги по айди c использванием графа")
    void getBookByIdWithGraphTest() {
        var bookByIdWithGraphOpt = bookDao.getByIdWithGraph(1L, BOOK_AUTHORS_GRAPH);
        assertTrue(bookByIdWithGraphOpt.isPresent());
    }

    @Test
    @DisplayName("Удаление книги")
    void deleteBookByIdTest() {
        var byIdOpt = bookDao.getById(1L);
        assertTrue(byIdOpt.isPresent());
        bookDao.delete(byIdOpt.get());
        var bookOpt = bookDao.getById(1L);
        assertTrue(bookOpt.isEmpty());
    }

    @Test
    @DisplayName("Сохранение книги в БД")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void createBookTest() {
        var bookName = "Created Book";
        var testCreatedBook = Book
                .builder()
                .bookName(bookName)
                .build();
        bookDao.create(testCreatedBook);

        var bookByIdOpt = bookDao.getById(10L);

        assertTrue(bookByIdOpt.isPresent());
        assertEquals(bookName, bookByIdOpt.get().getBookName());
    }

    @Test
    @DisplayName("Сохранение книги в БД c полной информацией")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void createBookWithInfoTest() {
        var bookName = "Created Book";
        var testCreatedBook = Book
                .builder()
                .bookName(bookName)
                .bookAuthors(List.of(Author.builder()
                        .fio("Created author")
                        .build()))
                .bookGenres(List.of(Genre.builder()
                        .genreName("Created genre")
                        .build()))
                .bookComments(List.of(Comment.builder()
                        .commentText("Created comment")
                        .build()))
                .build();
        bookDao.create(testCreatedBook);

        var bookByIdOpt = bookDao.getById(11L);

        assertTrue(bookByIdOpt.isPresent());
        assertEquals(bookName, bookByIdOpt.get().getBookName());
    }
}