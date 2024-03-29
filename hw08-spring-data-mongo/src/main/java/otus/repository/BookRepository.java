package otus.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import otus.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {
    List<Book> getBooksByBookName(String bookName);

    List<Book> findAll(Sort sort);

    List<Book> findAllByOrderByBookNameDesc();
}