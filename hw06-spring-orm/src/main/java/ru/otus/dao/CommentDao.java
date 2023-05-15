package ru.otus.dao;

import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    void create(Comment comment);

    Optional<Comment> getById(long comId);

    List<Comment> getAll();

    int deleteById(long comId);
}