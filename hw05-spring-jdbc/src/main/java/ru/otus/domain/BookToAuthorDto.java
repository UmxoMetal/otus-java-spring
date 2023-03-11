package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookToAuthorDto {
    private Long booId;
    private Long autId;
    private String fio;
}