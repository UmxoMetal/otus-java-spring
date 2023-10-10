package otus.service;

import otus.domain.Author;

public interface AuthorService {
    Author getAuthorById(String authorId);

    Boolean isAuthorPresentInBooks(Author author);
}