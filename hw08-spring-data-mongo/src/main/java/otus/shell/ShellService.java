package otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.domain.Book;
import otus.domain.Comment;
import otus.service.AuthorService;
import otus.service.BookService;
import otus.ui.BookPrinterService;
import java.util.List;

import static java.lang.String.*;
import static java.util.Comparator.comparing;

@ShellComponent
@RequiredArgsConstructor
public class ShellService {

    private final BookService bookService;
    private final AuthorService authorService;
    private final BookPrinterService bookPrinterService;

    @ShellMethod(value = "Create book", key = {"s1"})
    public void createBook(String bookName) {
        bookService.createBook(Book.builder()
                .bookName(bookName)
                .build());
    }

    @ShellMethod(value = "Get book by id", key = {"s2"})
    public String getBookById(String booId) {
        return bookPrinterService.print(bookService.getBookById(booId));
    }

    @ShellMethod(value = "Get book by name", key = {"s3"})
    public String getBookByBookName(String bookName) {
        return bookPrinterService.print(bookService.getBookByBookName(bookName));
    }

    @ShellMethod(value = "Get all books", key = {"s4"})
    public List<String> getAllBooks() {
        return bookService.getAllBooks()
                .stream()
                .sorted(comparing(Book::getBookName))
                .map(bookPrinterService::print)
                .toList();
    }

    @ShellMethod(value = "Delete book by id", key = {"s5"})
    public void deleteBookById(String booId) {
        bookService.deleteBookById(booId);
    }

    @ShellMethod(value = "Update book rating by id", key = {"s6"})
    public void updateBookRating(String booId, Short bookRating) {
        bookService.updateBookRatingById(booId, bookRating);
    }

    @ShellMethod(value = "Check author presence in books by author id", key = {"s7"})
    public String isAuthorPresentInBooks(String autId) {
        System.out.println(authorService.getAuthorById(autId));
        return authorService.isAuthorPresentInBooks(authorService.getAuthorById(autId)) ?
                "Автор найден в книгах" :
                "Автор не найден в книгах";
    }

    @ShellMethod(value = "Update book rating by id", key = {"s8"})
    public String getAllBookComments() {
        return join("\n", bookService.getAllBooks()
                .stream()
                .flatMap(book -> book.getBookComments()
                        .stream())
                .sorted(comparing(Comment::getCommentDate))
                .map(Comment::toString)
                .toList());
    }
}