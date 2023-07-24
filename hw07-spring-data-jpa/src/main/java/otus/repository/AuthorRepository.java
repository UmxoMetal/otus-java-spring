package otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}