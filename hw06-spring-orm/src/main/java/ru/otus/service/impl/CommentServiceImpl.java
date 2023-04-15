package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.CommentDao;
import ru.otus.domain.Comment;
import ru.otus.exception.BookServiceException;
import ru.otus.service.CommentService;

import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final PlatformTransactionManager transactionalManager;

    private static final String MSG_COMMENT_NOT_FOUND = "Comment with id %s not found";
    private static final String MSG_EMPTY_COMMENT_TABLE = "Comment table is empty";
    private static final String MSG_DELETION_FAILED = "Comment deletion by id failed";

    @Override
    @Transactional
    public void createComment(Comment comment) {
        commentDao.createComment(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(long comId) {
        return commentDao.getCommentById(comId)
                .orElseThrow(() -> new BookServiceException(format(MSG_COMMENT_NOT_FOUND, comId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllComments() {
        var allComments = commentDao.getAllComments();
        if (isEmpty(allComments)) {
            throw new BookServiceException(MSG_EMPTY_COMMENT_TABLE);
        }
        return allComments;
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteCommentById(long comId) {
        if (commentDao.deleteCommentById(comId) == 0) {
            throw new BookServiceException(MSG_DELETION_FAILED);
        }
    }
}