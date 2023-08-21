package otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}