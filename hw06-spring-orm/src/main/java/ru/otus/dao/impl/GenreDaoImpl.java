package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createGenre(Genre genre) {
        em.persist(genre);
    }

    @Override
    public Optional<Genre> getGenreById(long genId) {
        return ofNullable(em.find(Genre.class, genId));
    }

    @Override
    public List<Genre> getAllGenres() {
        return em.createQuery("select g from Genre g", Genre.class)
                .getResultList();
    }

    @Override
    public int deleteGenreById(long genId) {
        return em.createQuery("delete from Genre g where g.genId = :genId")
                .setParameter("genId", genId)
                .executeUpdate();
    }
}