package otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String>, AuthorRepositoryCustom {
}