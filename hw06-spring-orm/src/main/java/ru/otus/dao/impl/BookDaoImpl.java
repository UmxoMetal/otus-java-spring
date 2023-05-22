package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookDao;
import ru.otus.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static java.util.Map.*;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {

    private static final String JAVAX_PERSISTENCE_FETCHGRAPH = "javax.persistence.fetchgraph";

    public static final String BOOK_AUTHORS_GRAPH = "Book.bookAuthors";
    public static final String BOOK_GENRES_GRAPH = "Book.bookGenres";
    public static final String BOOK_COMMENTS_GRAPH = "Book.bookComments";

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Book book) {
        em.persist(book);
    }

    @Override
    public Optional<Book> getById(long booId) {
        return ofNullable(em.find(Book.class, booId));
    }

    @Override
    public Optional<Book> getByIdWithGraph(long booId, String graphName) {
        var entityGraph = em.getEntityGraph(graphName);
        return ofNullable(em.find(Book.class, booId, of(JAVAX_PERSISTENCE_FETCHGRAPH, entityGraph)));
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select b from Book b order by b.booId", Book.class)
                .getResultList();
    }

    @Override
    public List<Book> getAllWithGraph(String graphName) {
        var entityGraph = em.getEntityGraph(graphName);
        return em.createQuery("select b from Book b order by b.booId", Book.class)
                .setHint(JAVAX_PERSISTENCE_FETCHGRAPH, entityGraph)
                .getResultList();
    }

    @Override
    public void delete(Book book) {
        em.remove(book);
    }
}