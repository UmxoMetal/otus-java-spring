package otus.ui.impl;

import org.springframework.stereotype.Service;
import otus.domain.Book;
import otus.domain.Comment;
import otus.domain.Genre;
import otus.ui.BookPrinterService;

import static java.lang.String.format;
import static java.util.Objects.*;
import static java.util.stream.Collectors.joining;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class BookPrinterServiceImpl implements BookPrinterService {

    private static final String BASE_BOOK_PRINTER_TEMPLATE = """
            |||||||||||||||||||||||||||
            Book information:\040
            book id:[%s]\040
            book name:[%s]
            """;
    private static final String AUTHORS_PRINTER_TEMPLATE = "book authors:[%s]\n";
    private static final String GENRES_PRINTER_TEMPLATE = "book genres:[%s]\n";
    private static final String COMMENTS_PRINTER_TEMPLATE = "book comments:[%s]\n";
    private static final String RATING_PRINTER_TEMPLATE = "book rating:[%s]";

    @Override
    public String print(Book book) {
        return BASE_BOOK_PRINTER_TEMPLATE.formatted(book.getBooId(), book.getBookName())
                + prepareAuthors(book)
                + prepareGenres(book)
                + prepareComments(book)
                + prepareRating(book);
    }

    private String prepareAuthors(Book book) {
        final var bookAuthor = book.getBookAuthor();
        return format(AUTHORS_PRINTER_TEMPLATE, isNull(bookAuthor) ?
                "No info" :
                bookAuthor.getFio());
    }

    private String prepareGenres(Book book) {
        final var bookGenres = book.getBookGenres();
        return format(GENRES_PRINTER_TEMPLATE, isEmpty(bookGenres) ?
                "No info" :
                bookGenres
                        .stream()
                        .map(Genre::getGenreName)
                        .collect(joining(", ")));
    }

    private String prepareComments(Book book) {
        final var bookComments = book.getBookComments();
        return format(COMMENTS_PRINTER_TEMPLATE, isEmpty(bookComments) ?
                "No info" :
                bookComments
                        .stream()
                        .map(Comment::getCommentText)
                        .collect(joining(", ")));
    }

    private String prepareRating(Book book) {
        final var bookRating = book.getBookRating();
        return format(RATING_PRINTER_TEMPLATE, isNull(bookRating) ?
                "No info" :
                bookRating);
    }
}