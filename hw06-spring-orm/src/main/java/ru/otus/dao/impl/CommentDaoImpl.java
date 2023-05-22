package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.dao.CommentDao;
import ru.otus.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Comment comment) {
        em.persist(comment);
    }

    @Override
    public Optional<Comment> getById(long comId) {
        return ofNullable(em.find(Comment.class, comId));
    }

    @Override
    public List<Comment> getAll() {
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    @Override
    public void deleteById(Comment comment) {
       em.remove(comment);
    }
}