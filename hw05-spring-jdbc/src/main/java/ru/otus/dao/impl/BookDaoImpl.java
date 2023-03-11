package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dao.BookDao;
import ru.otus.domain.BookDto;

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