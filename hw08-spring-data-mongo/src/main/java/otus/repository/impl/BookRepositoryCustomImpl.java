package otus.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import otus.domain.Book;
import otus.repository.BookRepositoryCustom;

@Repository
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public void updateBookRatingById(String booId, Short bookRating) {
        final var update = new Update();
        update.set("bookRating", bookRating);
        mongoTemplate.findAndModify(new Query(Criteria.where("booId")
                .is(booId)), update, Book.class);
    }
}