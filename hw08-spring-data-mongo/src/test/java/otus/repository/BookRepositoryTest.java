package otus.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import otus.domain.Author;
import otus.domain.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.data.domain.Sort.*;
import static org.springframework.data.domain.Sort.Order.asc;

@DataMongoTest
public class BookRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    public void testSaveAndFindBook() {
        Author savedAuthor = authorRepository.save(Author.builder()
                .fio("Ivan Ivanov")
                .build());

        Book savedBook = bookRepository.save(Book.builder()
                .bookName("Sample Book")
                .bookAuthor(savedAuthor)
                .build());

        assertThat(savedBook.getBookId())
                .isNotEmpty();
        Optional<Book> bookOpt = bookRepository.findById(savedBook.getBookId());

        assertTrue(bookOpt.isPresent());
        assertThat(bookOpt.get())
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(savedBook);
    }

    @Test
    public void testUpdateBookRatingById() {
        Author savedAuthor = authorRepository.save(Author.builder()
                .fio("Ivan Ivanov")
                .build());

        Book savedBook = bookRepository.save(Book.builder()
                .bookName("Sample Book")
                .bookAuthor(savedAuthor)
                .build());

        assertThat(savedBook.getBookId())
                .isNotEmpty();
        assertThat(savedBook.getBookRating())
                .isNull();

        short newRating = 5;
        bookRepository.updateBookRatingById(savedBook.getBookId(), newRating);

        Optional<Book> updatedBookOpt = bookRepository.findById(savedBook.getBookId());
        assertTrue(updatedBookOpt.isPresent());
        assertThat(updatedBookOpt.get()
                .getBookRating())
                .isEqualTo(newRating);
    }

    @Test
    public void testFindAllWithAscSort() {
        bookRepository.save(Book.builder()
                .bookName("A")
                .build());
        bookRepository.save(Book.builder()
                .bookName("B")
                .build());

        List<Book> books = bookRepository.findAll(by(asc("bookName")));

        assertThat(books)
                .isNotEmpty();
        assertThat(books.get(0).getBookName()).isEqualTo("A");
        assertThat(books.get(1).getBookName()).isEqualTo("B");
    }

    @Test
    public void testFindAllWithDescSort() {
        bookRepository.save(Book.builder()
                .bookName("A")
                .build());
        bookRepository.save(Book.builder()
                .bookName("B")
                .build());

        List<Book> books = bookRepository.findAllByOrderByBookNameDesc();

        assertThat(books)
                .isNotEmpty();
        assertThat(books.get(0).getBookName()).isEqualTo("B");
        assertThat(books.get(1).getBookName()).isEqualTo("A");
    }
}