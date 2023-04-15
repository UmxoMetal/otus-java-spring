package ru.otus.dao;

import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    void createComment(Comment comment);

    Optional<Comment> getCommentById(Long comId);

    List<Comment> getAllComments();

    int deleteCommentById(Long comId);
}