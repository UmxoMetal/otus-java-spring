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
    public void create(Author author) {
        em.persist(author);
    }

    @Override
    public Optional<Author> getById(long autId) {
        return ofNullable(em.find(Author.class, autId));
    }

    @Override
    public List<Author> getAll() {
        return em.createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    @Override
    public void delete(Author author) {
        em.remove(author);
    }
}