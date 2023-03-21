package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.BookDto;
import ru.otus.service.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookShellService {
    private final BookService bookService;

    @ShellMethod(value = "Create book", key = {"book-create"})
    public void createBook(String bookName) {
        bookService.createBook(BookDto.builder()
                .bookName(bookName)
                .build());
    }

    @ShellMethod(value = "Get book by id", key = {"book-get-by-id"})
    public BookDto getBookById(Long booId) {
        return bookService.getBookById(booId);
    }

    @ShellMethod(value = "Get book by id with full info", key = {"book-get-by-id-with-info"})
    public BookDto getBookByIdWithFullInfo(Long booId) {
        return bookService.getBookByIdWithFullInfo(booId);
    }

    @ShellMethod(value = "Get all books", key = {"book-get-all"})
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @ShellMethod(value = "Get all books with info", key = {"book-get-all-with-info"})
    public List<BookDto> getAllBooksWithInfo() {
        return bookService.getAllBooksWithFullInfo();
    }

    @ShellMethod(value = "Delete book by id", key = {"book-delete-by-id"})
    public void deleteBookById(Long booId) {
        bookService.deleteBookById(booId);
    }
}