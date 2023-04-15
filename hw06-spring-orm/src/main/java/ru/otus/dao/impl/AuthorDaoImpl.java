package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createAuthor(Author author) {
        em.persist(author);
    }

    @Override
    public Optional<Author> getAuthorById(Long autId) {
        return ofNullable(em.find(Author.class, autId));
    }

    @Override
    public List<Author> getAllAuthors() {
        return em.createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    @Override
    public int deleteAuthorById(Long autId) {
        return em.createQuery("delete from Author a where a.autId = :autId")
                .setParameter("autId", autId)
                .executeUpdate();
    }
}