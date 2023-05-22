package ru.otus.service;

import ru.otus.domain.Comment;

import java.util.List;

public interface CommentService {
    void createComment(Comment comment);

    Comment getCommentById(long comId);

    List<Comment> getAllComments();

    void deleteCommentById(long comId);
}