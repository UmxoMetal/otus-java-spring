package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dao.GenreDao;
import ru.otus.domain.BookToGenreDto;
import ru.otus.domain.GenreDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.springframework.dao.support.DataAccessUtils.singleResult;

@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void createGenre(GenreDto genreDto) {
        namedParameterJdbcOperations.update("INSERT INTO GENRE VALUES (NEXTVAL('GENRE_SEQ'), :genreName)",
                new MapSqlParameterSource("genreName", genreDto.getGenreName()));
    }

    @Override
    public Optional<GenreDto> getGenreById(Long genId) {
        return ofNullable(singleResult(namedParameterJdbcOperations.query(
                "SELECT GEN_ID, GENRE_NAME FROM GENRE WHERE GEN_ID =:genId",
                new MapSqlParameterSource("genId", genId),
                (rs, rowNum) -> GenreDto
                        .builder()
                        .genId(rs.getLong("GEN_ID"))
                        .genreName(rs.getString("GENRE_NAME"))
                        .build())));
    }

    @Override
    public List<GenreDto> getAllGenres() {
        return namedParameterJdbcOperations.query("SELECT GEN_ID, GENRE_NAME FROM GENRE",
                (rs, rowNum) -> GenreDto
                        .builder()
                        .genId(rs.getLong("GEN_ID"))
                        .genreName(rs.getString("GENRE_NAME"))
                        .build());
    }

    @Override
    public List<GenreDto> getAllGenresByBooId(Long booId) {
        return namedParameterJdbcOperations.query("""
                        SELECT genr.GEN_ID, genr.GENRE_NAME FROM GENRE genr 
                        JOIN BOOK_TO_GENRE btg ON genr.GEN_ID = btg.GEN_ID 
                        WHERE btg.BOO_ID =:booId
                        """, new MapSqlParameterSource("booId", booId),
                (rs, rowNum) -> GenreDto
                        .builder()
                        .genId(rs.getLong("GEN_ID"))
                        .genreName(rs.getString("GENRE_NAME"))
                        .build());
    }

    @Override
    public List<BookToGenreDto> getAllGenresWithBooId() {
        return namedParameterJdbcOperations.query("""
                SELECT btg.BOO_ID, genr.GEN_ID, genr.GENRE_NAME
                 FROM GENRE genr
                 JOIN BOOK_TO_GENRE btg
                ON  btg.GEN_ID = genr.GEN_ID
                """, (rs, rowNum) -> BookToGenreDto
                .builder()
                .booId(rs.getLong("BOO_ID"))
                .genId(rs.getLong("GEN_ID"))
                .genreName(rs.getString("GENRE_NAME"))
                .build());
    }

    @Override
    public int deleteGenreById(Long genId) {
        return namedParameterJdbcOperations.update("DELETE FROM GENRE WHERE GEN_ID =:genId",
                new MapSqlParameterSource("genId", genId));
    }
}