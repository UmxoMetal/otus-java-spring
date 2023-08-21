package otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}