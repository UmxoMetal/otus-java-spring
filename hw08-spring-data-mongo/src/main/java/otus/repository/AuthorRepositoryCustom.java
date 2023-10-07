package otus.repository;

import otus.domain.Author;

public interface AuthorRepositoryCustom {
    boolean isAuthorPresentInBooks(Author author);
}