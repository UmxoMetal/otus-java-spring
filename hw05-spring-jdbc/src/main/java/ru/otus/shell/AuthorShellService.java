package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.AuthorDto;
import ru.otus.service.AuthorService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellService {
    private final AuthorService authorService;

    @ShellMethod(value = "Create author", key = {"author-create"})
    public void createAuthor(String fio) {
        authorService.createAuthor(AuthorDto.builder()
                .fio(fio)
                .build());
    }

    @ShellMethod(value = "Get author by id", key = {"author-get-by-id"})
    public AuthorDto getAuthorById(Long autId) {
        return authorService.getAuthorById(autId);
    }

    @ShellMethod(value = "Get all authors", key = {"author-get-all"})
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @ShellMethod(value = "Delete author by id", key = {"author-delete-by-id"})
    public void deleteAuthorById(Long autId) {
        authorService.deleteAuthorById(autId);
    }
}