package otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.domain.Comment;
import otus.exception.BookServiceException;
import otus.repository.CommentRepository;
import otus.service.CommentService;


import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private static final String MSG_COMMENT_NOT_FOUND = "Comment with id %s not found";
    private static final String MSG_EMPTY_COMMENT_TABLE = "Comment table is empty";

    @Override
    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(long comId) {
        return commentRepository.findById(comId)
                .orElseThrow(() -> new BookServiceException(format(MSG_COMMENT_NOT_FOUND, comId)));
    }

    @Override
    public List<Comment> getAllComments() {
        var allComments = commentRepository.findAll();
        if (isEmpty(allComments)) {
            throw new BookServiceException(MSG_EMPTY_COMMENT_TABLE);
        }
        return allComments;
    }

    @Override
    public void deleteCommentById(long comId) {
        commentRepository.deleteById(comId);
    }
}