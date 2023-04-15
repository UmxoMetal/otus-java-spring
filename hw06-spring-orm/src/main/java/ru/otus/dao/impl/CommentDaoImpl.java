package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.dao.CommentDao;
import ru.otus.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createComment(Comment comment) {
        em.persist(comment);
    }

    @Override
    public Optional<Comment> getCommentById(Long comId) {
        return ofNullable(em.find(Comment.class, comId));
    }

    @Override
    public List<Comment> getAllComments() {
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    @Override
    public int deleteCommentById(Long comId) {
        return em.createQuery("delete from Comment c where c.comId = :comId")
                .setParameter("comId", comId)
                .executeUpdate();
    }
}