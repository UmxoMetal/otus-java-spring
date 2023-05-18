package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.ui.BookPrinterService;
import ru.otus.service.BookService;
import java.util.List;

import static ru.otus.dao.impl.BookDaoImpl.*;

@ShellComponent
@RequiredArgsConstructor
public class BookShellService {

    private final BookService bookService;
    private final BookPrinterService bookPrinterService;

    @ShellMethod(value = "Create book", key = {"book-create"})
    public void createBook(String bookName) {
        bookService.createBook(Book.builder()
                .bookName(bookName)
                .build());
    }

    @ShellMethod(value = "Get book by id", key = {"book-get-by-id"})
    @Transactional
    public String getBookById(long booId) {
        //return bookPrinterService.print(bookService.getBookById(booId));
        Book bookById = bookService.getBookById(booId);
        List<Author> bookAuthors = bookById.getBookAuthors();
        System.out.println(bookAuthors);
        return null;
    }

    @ShellMethod(value = "Get book by id with author", key = {"book-get-by-id-with-author"})
    public String getBookByIdWithAuthor(long booId) {
        return bookPrinterService.printWithAuthors(bookService.getBookByIdWithGraph(booId, BOOK_AUTHORS_GRAPH));
    }

    @ShellMethod(value = "Get book by id with genre", key = {"book-get-by-id-with-genre"})
    public String getBookByIdWithGenre(long booId) {
        return bookPrinterService.printWithGenres(bookService.getBookByIdWithGraph(booId, BOOK_GENRES_GRAPH));
    }

    @ShellMethod(value = "Get book by id with comment", key = {"book-get-by-id-with-comment"})
    public String getBookByIdWithComment(long booId) {
       return bookPrinterService.printWithComments(bookService.getBookByIdWithGraph(booId, BOOK_COMMENTS_GRAPH));
    }

    @ShellMethod(value = "Get book by id with full info", key = {"book-get-by-id-with-info"})
    public String getBookByIdWithFullInfo(long booId) {
        return bookPrinterService.printWithFullInfo(bookService.getBookByIdWithFullInfo(booId));
    }

    @ShellMethod(value = "Get all books", key = {"book-get-all"})
    public List<String> getAllBooks() {
        return bookService.getAllBooks()
                .stream()
                .map(bookPrinterService::print)
                .toList();
    }

    @ShellMethod(value = "Get all books with info", key = {"book-get-all-with-info"})
    public List<String> getAllBooksWithInfo() {
        return bookService.getAllBooksWithInfo()
                .stream()
                .map(bookPrinterService::printWithFullInfo)
                .toList();
    }

    @ShellMethod(value = "Delete book by id", key = {"book-delete-by-id"})
    public void deleteBookById(long booId) {
        bookService.deleteBookById(booId);
    }
}