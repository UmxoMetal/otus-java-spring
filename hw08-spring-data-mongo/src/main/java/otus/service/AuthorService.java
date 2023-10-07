package otus.service;

import otus.domain.Author;

public interface AuthorService {
    Author getAuthorById(String autId);

    Boolean isAuthorPresentInBooks(Author author);
}