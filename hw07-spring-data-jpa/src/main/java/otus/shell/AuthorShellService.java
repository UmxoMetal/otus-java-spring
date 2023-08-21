package otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.domain.Author;
import otus.service.AuthorService;

import java.util.List;

import static java.util.Comparator.comparing;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellService {

    private final AuthorService authorService;

    @ShellMethod(value = "Create author", key = {"author-create"})
    public void createAuthor(String fio) {
        authorService.createAuthor(Author.builder()
                .fio(fio)
                .build());
    }

    @ShellMethod(value = "Get author by id", key = {"author-get-by-id"})
    public Author getAuthorById(long autId) {
        return authorService.getAuthorById(autId);
    }

    @ShellMethod(value = "Get all authors", key = {"author-get-all"})
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors()
                .stream()
                .sorted(comparing(Author::getFio))
                .toList();
    }

    @ShellMethod(value = "Delete author by id", key = {"author-delete-by-id"})
    public void deleteAuthorById(long autId) {
        authorService.deleteAuthorById(autId);
    }
}