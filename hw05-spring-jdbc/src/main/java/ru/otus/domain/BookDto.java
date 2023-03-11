package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.*;
import static java.util.stream.Collectors.*;
import static org.apache.commons.collections4.CollectionUtils.*;

@Data
@Builder
@AllArgsConstructor
public class BookDto {
    private Long booId;
    private String bookName;
    @Builder.Default
    private List<AuthorDto> bookAuthors = new ArrayList<>();
    @Builder.Default
    private List<GenreDto> bookGenres = new ArrayList<>();

    @Override
    public String toString() {
        return format("""
                        Book information: 
                        book id:[%d] 
                        book name:[%s] 
                        book authors:[%s] 
                        book genres:[%s]
                        """, booId, bookName,
                isEmpty(bookAuthors) ?
                        "No info" :
                        bookAuthors.stream()
                                .map(AuthorDto::getFio)
                                .collect(joining(",")),
                isEmpty(bookGenres) ?
                        "No info" :
                        bookGenres.stream()
                                .map(GenreDto::getGenreName)
                                .collect(joining(",")));
    }
}