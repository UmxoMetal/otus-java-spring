package otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.domain.Author;
import otus.exception.BookServiceException;
import otus.repository.AuthorRepository;
import otus.service.AuthorService;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private static final String MSG_AUTHOR_NOT_FOUND = "Author with id %s not found";

    @Override
    public Author getAuthorById(String authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new BookServiceException(format(MSG_AUTHOR_NOT_FOUND, authorId)));
    }

    @Override
    public Boolean isAuthorPresentInBooks(Author author) {
        return authorRepository.isAuthorPresentInBooks(author);
    }
}