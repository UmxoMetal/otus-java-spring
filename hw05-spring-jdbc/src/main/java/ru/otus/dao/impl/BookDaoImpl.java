package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dao.BookDao;
import ru.otus.domain.BookDto;
import ru.otus.domain.BookToGenreAndAuthorDto;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.springframework.dao.support.DataAccessUtils.singleResult;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void createBook(BookDto bookDto) {
        namedParameterJdbcOperations.update("INSERT INTO BOOK VALUES (NEXTVAL('BOOK_SEQ'), :bookName)",
                new MapSqlParameterSource("bookName", bookDto.getBookName()));
    }

    @Override
    public Optional<BookDto> getBookById(Long booId) {
        return ofNullable(singleResult(namedParameterJdbcOperations.query(
                "SELECT BOO_ID, BOOK_NAME FROM BOOK WHERE BOO_ID =:booId",
                new MapSqlParameterSource("booId", booId),
                (rs, rowNum) -> BookDto
                        .builder()
                        .booId(rs.getLong("BOO_ID"))
                        .bookName(rs.getString("BOOK_NAME"))
                        .build())));
    }

    @Override
    public List<BookToGenreAndAuthorDto> getBookByIdWithFullInfo(Long booId) {
        return namedParameterJdbcOperations.query(
                """
                         SELECT boo.BOO_ID, boo.BOOK_NAME, auth.AUTHOR_FIO,
                           genr.GENRE_NAME FROM BOOK boo
                           LEFT JOIN BOOK_TO_AUTHOR bta
                                                ON bta.BOO_ID = boo.BOO_ID
                           LEFT JOIN AUTHOR auth
                                               ON auth.AUT_ID = bta.AUT_ID
                           LEFT JOIN BOOK_TO_GENRE btg
                                              ON btg.BOO_ID = boo.BOO_ID
                            LEFT  JOIN GENRE genr
                                               ON genr.GEN_ID = btg.GEN_ID
                        WHERE boo.BOO_ID =:booId
                        """,
                new MapSqlParameterSource("booId", booId),
                (rs, rowNum) -> BookToGenreAndAuthorDto
                        .builder()
                        .booId(rs.getLong("BOO_ID"))
                        .bookName(rs.getString("BOOK_NAME"))
                        .fio(rs.getString("AUTHOR_FIO"))
                        .genreName(rs.getString("GENRE_NAME"))
                        .build());
    }

    @Override
    public List<BookDto> getAllBooks() {
        return namedParameterJdbcOperations.query("SELECT BOO_ID, BOOK_NAME FROM BOOK",
                (rs, rowNum) -> BookDto
                        .builder()
                        .booId(rs.getLong("BOO_ID"))
                        .bookName(rs.getString("BOOK_NAME"))
                        .build());
    }

    @Override
    public int deleteBookById(Long booId) {
        return namedParameterJdbcOperations.update("DELETE FROM BOOK WHERE BOO_ID =:booId",
                new MapSqlParameterSource("booId", booId));
    }
}