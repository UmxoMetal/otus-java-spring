package otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.domain.Book;
import otus.domain.Comment;
import otus.service.AuthorService;
import otus.service.BookService;
import otus.ui.PrintService;
import java.util.List;

import static io.changock.utils.CollectionUtils.*;
import static java.util.Comparator.comparing;

@ShellComponent
@RequiredArgsConstructor
public class ShellService {

    private final BookService bookService;
    private final AuthorService authorService;
    private final PrintService printService;

    @ShellMethod(value = "Create book", key = {"s1"})
    public String createBook(String bookName) {
        return printService.print(bookService.create(Book.builder()
                .bookName(bookName)
                .build()));
    }

    @ShellMethod(value = "Get book by id", key = {"s2"})
    public String getBookById(String bookId) {
        return printService.print(bookService.getBookById(bookId));
    }

    @ShellMethod(value = "Get book by name", key = {"s3"})
    public String getBookByBookName(String bookName) {
        return printService.print(bookService.getBookByBookName(bookName));
    }

    @ShellMethod(value = "Get all books sorted asc", key = {"s4"})
    public List<String> getAllBooksAsc() {
        return bookService.getAllBooksSortedAsc()
                .stream()
                .map(printService::print)
                .toList();
    }

    @ShellMethod(value = "Get all books sorted desc", key = {"s5"})
    public List<String> getAllBooksDesc() {
        return bookService.getAllBooksSortedDesc()
                .stream()
                .map(printService::print)
                .toList();
    }

    @ShellMethod(value = "Delete book by id", key = {"s6"})
    public void deleteBookById(String bookId) {
        bookService.deleteBookById(bookId);
    }

    @ShellMethod(value = "Update book rating by id", key = {"s7"})
    public void updateBookRating(String bookId, Short bookRating) {
        bookService.updateBookRatingById(bookId, bookRating);
    }

    @ShellMethod(value = "Check author presence in books by author id", key = {"s8"})
    public String isAuthorPresentInBooks(String authorId) {
        return authorService.isAuthorPresentInBooks(authorService.getAuthorById(authorId)) ?
                "Автор найден в книгах" :
                "Автор не найден в книгах";
    }

    @ShellMethod(value = "Get all book comments", key = {"s9"})
    public List<String> getAllBookComments() {
        return bookService.getAllBooks()
                .stream()
                .filter(book -> isNotNullOrEmpty(book.getBookComments()))
                .flatMap(book -> book.getBookComments()
                        .stream())
                .sorted(comparing(Comment::getCommentDate))
                .map(printService::print)
                .toList();
    }
}