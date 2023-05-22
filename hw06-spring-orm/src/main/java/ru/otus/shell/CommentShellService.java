package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.Comment;
import ru.otus.service.CommentService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommentShellService {

    private final CommentService commentService;

    @ShellMethod(value = "Create comment", key = {"comment-create"})
    public void createComment(String commentText) {
        commentService.createComment(Comment.builder()
                .commentText(commentText)
                .build());
    }

    @ShellMethod(value = "Get comment by id", key = {"comment-get-by-id"})
    public Comment getCommentById(long comId) {
        return commentService.getCommentById(comId);
    }

    @ShellMethod(value = "Get all comments", key = {"comment-get-all"})
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @ShellMethod(value = "Delete comment by id", key = {"comment-delete-by-id"})
    public void deleteCommentById(long comId) {
        commentService.deleteCommentById(comId);
    }
}