package otus.ui.impl;

import org.springframework.stereotype.Service;
import otus.domain.Author;
import otus.domain.Book;
import otus.domain.Comment;
import otus.domain.Genre;
import otus.ui.BookPrinterService;


import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class BookPrinterServiceImpl implements BookPrinterService {

    private static final String BASE_BOOK_PRINTER_TEMPLATE = """
               Book information:\040
            book id:[%d]\040
            book name:[%s]\040
            """;
    private static final String AUTHORS_PRINTER_TEMPLATE = "book authors:[%s]\n";
    private static final String GENRES_PRINTER_TEMPLATE = "book genres:[%s]\n";
    private static final String COMMENTS_PRINTER_TEMPLATE = "book comments:[%s]\n";

    @Override
    public String print(Book book) {
        return format(BASE_BOOK_PRINTER_TEMPLATE, book.getBooId(), book.getBookName());
    }

    @Override
    public String printWithAuthors(Book book) {
        return print(book) + prepareAuthors(book);
    }

    @Override
    public String printWithGenres(Book book) {
        return print(book) + prepareGenres(book);
    }

    @Override
    public String printWithFullInfo(Book book) {
        return print(book) + prepareAuthors(book) + prepareGenres(book) + prepareComments(book);
    }

    @Override
    public String printWithComments(Book book) {
        return print(book) + prepareComments(book);

    }

    private String prepareAuthors(Book book) {
        var bookAuthors = book.getBookAuthors();
        return format(AUTHORS_PRINTER_TEMPLATE, isEmpty(bookAuthors) ?
                "No info" :
                bookAuthors
                        .stream()
                        .map(Author::getFio)
                        .collect(joining(", ")));
    }

    private String prepareGenres(Book book) {
        var bookGenres = book.getBookGenres();
        return format(GENRES_PRINTER_TEMPLATE, isEmpty(bookGenres) ?
                "No info" :
                bookGenres
                        .stream()
                        .map(Genre::getGenreName)
                        .collect(joining(", ")));
    }

    private String prepareComments(Book book) {
        var bookComments = book.getBookComments();
        return format(COMMENTS_PRINTER_TEMPLATE, isEmpty(bookComments) ?
                "No info" :
                bookComments
                        .stream()
                        .map(Comment::getCommentText)
                        .collect(joining(", ")));
    }
}