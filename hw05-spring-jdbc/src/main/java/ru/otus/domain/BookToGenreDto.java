package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookToGenreDto {
    private Long booId;
    private Long genId;
    private String genreName;
}