package otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.domain.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String>, AuthorRepositoryCustom {
    List<Author> findByFio(String fio);
}