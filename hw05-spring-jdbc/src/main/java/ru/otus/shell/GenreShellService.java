package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.AuthorDto;
import ru.otus.domain.GenreDto;
import ru.otus.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreShellService {
    private final GenreService genreService;

    @ShellMethod(value = "Create genre", key = {"genre-create"})
    public void createGenre(String genreName) {
        genreService.createGenre(GenreDto.builder()
                .genreName(genreName)
                .build());
    }

    @ShellMethod(value = "Get genre by id", key = {"genre-get-by-id"})
    public GenreDto getGenreById(Long genId) {
        return genreService.getGenreById(genId);
    }

    @ShellMethod(value = "Get all genres", key = {"genre-get-all"})
    public List<GenreDto> getAllGenres() {
        return genreService.getAllGenres();
    }

    @ShellMethod(value = "Delete genre by id", key = {"genre-delete-by-id"})
    public void deleteGenreById(Long genId) {
        genreService.deleteGenreById(genId);
    }
}