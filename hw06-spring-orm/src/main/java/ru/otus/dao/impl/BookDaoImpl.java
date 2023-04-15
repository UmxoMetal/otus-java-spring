package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.dao.BookDao;
import ru.otus.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {

    private static final String JAVAX_PERSISTENCE_FETCHGRAPH = "javax.persistence.fetchgraph";

    public static final String BOOK_AUTHORS_GRAPH = "Book.bookAuthors";
    public static final String BOOK_GENRES_GRAPH = "Book.bookGenres";
    public static final String BOOK_COMMENTS_GRAPH = "Book.bookComments";

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createBook(Book book) {
        em.persist(book);
    }

    @Override
    public Optional<Book> getBookById(long booId) {
        return ofNullable(em.find(Book.class, booId));
    }

    @Override
    public Optional<Book> getBookByIdWithGraph(long booId, String graphName) {
        var entityGraph = em.getEntityGraph(graphName);
        return em.createQuery("select distinct b from Book b where b.booId = :booId", Book.class)
                .setParameter("booId", booId)
                .setHint(JAVAX_PERSISTENCE_FETCHGRAPH, entityGraph)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<Book> getAllBooks() {
        return em.createQuery("select b from Book b order by b.booId", Book.class)
                .getResultList();
    }

    @Override
    public List<Book> getAllBooksWithGraph(String graphName) {
        var entityGraph = em.getEntityGraph(graphName);
        return em.createQuery("select b from Book b order by b.booId", Book.class)
                .setHint(JAVAX_PERSISTENCE_FETCHGRAPH, entityGraph)
                .getResultList();
    }

    @Override
    public int deleteBookById(long booId) {
        return em.createQuery("delete from Book b where b.booId = :booId")
                .setParameter("booId", booId)
                .executeUpdate();
    }
}