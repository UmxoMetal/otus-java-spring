package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.Genre;
import ru.otus.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreShellService {

    private final GenreService genreService;

    @ShellMethod(value = "Create genre", key = {"genre-create"})
    public void createGenre(String genreName) {
        genreService.createGenre(Genre.builder()
                .genreName(genreName)
                .build());
    }

    @ShellMethod(value = "Get genre by id", key = {"genre-get-by-id"})
    public Genre getGenreById(long genId) {
        return genreService.getGenreById(genId);
    }

    @ShellMethod(value = "Get all genres", key = {"genre-get-all"})
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @ShellMethod(value = "Delete genre by id", key = {"genre-delete-by-id"})
    public void deleteGenreById(long genId) {
        genreService.deleteGenreById(genId);
    }
}