package otus.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import otus.domain.Book;

import java.util.List;
import java.util.Optional;

import static otus.service.impl.BookServiceImpl.BOOK_GENRES_GRAPH;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select distinct b from Book b left join fetch b.bookAuthors where b.booId =:booId")
    Optional<Book> findBookWithAuthorsByBooId(@Param("booId") long booId);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = BOOK_GENRES_GRAPH)
    Optional<Book> findBookWithGenresByBooId(@Param("booId") long booId);

    @Query("select distinct b from Book b left join fetch b.bookComments where b.booId =:booId")
    Optional<Book> findBookWithCommentsByBooId(@Param("booId") long booId);

    @Query("select distinct b from Book b left join fetch b.bookAuthors")
    List<Book> findBooksWithAuthors();

    @Query("select distinct b from Book b left join fetch b.bookGenres")
    List<Book> findBooksWithGenres();

    @Query("select distinct b from Book b left join fetch b.bookComments")
    List<Book> findBookWithComments();
}