package otus.migration;

import com.mongodb.client.MongoDatabase;
import io.changock.migration.api.annotations.ChangeLog;
import io.changock.migration.api.annotations.ChangeSet;
import otus.domain.Author;
import otus.domain.Book;
import otus.domain.Comment;
import otus.domain.Genre;
import otus.repository.AuthorRepository;
import otus.repository.BookRepository;
import otus.repository.GenreRepository;
import java.util.Date;
import java.util.List;

import static java.util.List.of;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private List<Genre> firstBookGenres;
    private List<Genre> secondBookGenres;

    private Author firstBookAuthor;

    @ChangeSet(order = "000", id = "dropDatabase", author = "admin", runAlways = true)
    public void dropDatabase(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initGenres", author = "admin", runAlways = true)
    public void initGenres(GenreRepository genreRepository) {
        firstBookGenres = of(
                genreRepository.save(Genre.builder()
                        .genreName("firstBookGenre1")
                        .build()),
                genreRepository.save(Genre.builder()
                        .genreName("firstBookGenre2")
                        .build())
        );
        secondBookGenres = of(
                genreRepository.save(Genre.builder()
                        .genreName("secondBookGenre1")
                        .build()),
                genreRepository.save(Genre.builder()
                        .genreName("secondBookGenre2")
                        .build()));
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "admin", runAlways = true)
    public void initAuthors(AuthorRepository authorRepository) {
        firstBookAuthor = authorRepository.save(Author.builder()
                .fio("firstBookAuthor")
                .build());
    }

    @ChangeSet(order = "003", id = "initBooks", author = "admin", runAlways = true)
    public void initBooks(BookRepository bookRepository) {
        final var currentDate = new Date();
        bookRepository.save(Book.builder()
                .bookName("firstBook")
                .bookGenres(firstBookGenres)
                .bookAuthor(firstBookAuthor)
                .bookComments(of(
                        Comment.builder()
                                .commentDate(currentDate)
                                .commentText("firstBook1Comment")
                                .build(),
                        Comment.builder()
                                .commentDate(new Date(currentDate.getTime() + 1000L))
                                .commentText("firstBook2Comment")
                                .build()))
                .build());
        bookRepository.save(Book.builder()
                .bookName("secondBook")
                .bookGenres(secondBookGenres)
                .bookComments(of(
                        Comment.builder()
                                .commentDate(new Date(currentDate.getTime() + 2000L))
                                .commentText("secondBook1Comment")
                                .build(),
                        Comment.builder()
                                .commentDate(new Date(currentDate.getTime() + 3000L))
                                .commentText("secondBook2Comment")
                                .build()))
                .build());
    }
}
