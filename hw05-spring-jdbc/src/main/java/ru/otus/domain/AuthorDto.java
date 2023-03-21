package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static java.lang.String.format;

@Data
@Builder
@AllArgsConstructor
public class AuthorDto {
    private Long autId;
    private String fio;

    @Override
    public String toString() {
        return format("""
                Author information
                author id:[%d]
                author fio:[%s]
                """, autId, fio);
    }
}