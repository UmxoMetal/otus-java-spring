package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.AuthorDto;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;
import static org.springframework.dao.support.DataAccessUtils.*;

@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void createAuthor(AuthorDto authorDto) {
        namedParameterJdbcOperations.update("INSERT INTO AUTHOR VALUES (NEXTVAL('AUTHOR_SEQ'), :authorFio)",
                new MapSqlParameterSource("authorFio", authorDto.getFio()));
    }

    @Override
    public Optional<AuthorDto> getAuthorById(Long autId) {
        return ofNullable(singleResult(namedParameterJdbcOperations.query(
                "SELECT AUT_ID, AUTHOR_FIO FROM AUTHOR WHERE AUT_ID =:autId",
                new MapSqlParameterSource("autId", autId),
                (rs, rowNum) -> AuthorDto
                        .builder()
                        .autId(rs.getLong("AUT_ID"))
                        .fio(rs.getString("AUTHOR_FIO"))
                        .build())));
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        return namedParameterJdbcOperations.query("SELECT AUT_ID, AUTHOR_FIO FROM AUTHOR",
                (rs, rowNum) -> AuthorDto
                        .builder()
                        .autId(rs.getLong("AUT_ID"))
                        .fio(rs.getString("AUTHOR_FIO"))
                        .build());
    }

    @Override
    public List<AuthorDto> getAllAuthorsByBooId(Long booId) {
        return namedParameterJdbcOperations.query("""
                        SELECT auth.AUT_ID, auth.AUTHOR_FIO FROM AUTHOR auth 
                        JOIN BOOK_TO_AUTHOR bta ON auth.AUT_ID = bta.AUT_ID 
                        WHERE bta.BOO_ID =:booId
                        """, new MapSqlParameterSource("booId", booId),
                (rs, rowNum) -> AuthorDto
                        .builder()
                        .autId(rs.getLong("AUT_ID"))
                        .fio(rs.getString("AUTHOR_FIO"))
                        .build());
    }

    @Override
    public int deleteAuthorById(Long autId) {
        return namedParameterJdbcOperations.update("DELETE FROM AUTHOR WHERE AUT_ID =:autId",
                new MapSqlParameterSource("autId", autId));
    }
}