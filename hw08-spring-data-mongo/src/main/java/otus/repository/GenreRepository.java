package otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}