package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookToGenreAndAuthorDto {
    private Long booId;
    private String bookName;
    private String fio;
    private String genreName;
}