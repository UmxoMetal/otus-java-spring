package otus.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import otus.domain.Author;
import otus.domain.Book;
import otus.repository.AuthorRepositoryCustom;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public boolean isAuthorPresentInBooks(Author author) {
        return mongoTemplate.exists(new Query(Criteria.where("author").is(author)), Book.class);
    }
}