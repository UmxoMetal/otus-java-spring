package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static java.lang.String.format;

@Data
@Builder
@AllArgsConstructor
public class GenreDto {
    private Long genId;
    private String genreName;

    @Override
    public String toString() {
        return format("""
                Genre information
                genre id:[%d]
                genre name:[%s]
                """, genId, genreName);
    }
}