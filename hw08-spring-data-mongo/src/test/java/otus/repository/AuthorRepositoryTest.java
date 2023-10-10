package otus.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import otus.domain.Author;
import otus.domain.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setUp() {
        authorRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    public void testSaveAndFindAuthor() {
        Author savedAuthor = authorRepository.save(Author.builder()
                .fio("Ivan Ivanov")
                .build());

        assertThat(savedAuthor.getAuthorId())
                .isNotEmpty();

        Optional<Author> authorOpt = authorRepository.findById(savedAuthor.getAuthorId());

        assertTrue(authorOpt.isPresent());
        assertThat(authorOpt.get())
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(savedAuthor);
    }
    @Test
    public void testFindByFio() {
        Author savedAuthor = authorRepository.save(Author.builder()
                .fio("Ivan Ivanov")
                .build());
        List<Author> authors = authorRepository.findByFio("Ivan Ivanov");

        assertThat(authors)
                .isNotEmpty();
        assertThat(authors.get(0))
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(savedAuthor);
    }

    @Test
    public void testIsAuthorPresentInBooks() {
        Author savedAuthor = authorRepository.save(Author.builder()
                .fio("Ivan Ivanov")
                .build());
        bookRepository.save(Book.builder()
                .bookAuthor(savedAuthor)
                .build());
        assertTrue(isAuthorPresentInBooks(savedAuthor));
    }

    private boolean isAuthorPresentInBooks(Author author) {
        return mongoTemplate.exists(new Query(Criteria.where("bookAuthor").is(author)), Book.class);
    }
}