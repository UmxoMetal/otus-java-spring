package otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.domain.Author;
import otus.exception.BookServiceException;
import otus.repository.AuthorRepository;
import otus.service.AuthorService;

import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.*;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private static final String MSG_AUTHOR_NOT_FOUND = "Author with id %s not found";
    private static final String MSG_EMPTY_AUTHOR_TABLE = "Author table is empty";

    @Override
    public void createAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public Author getAuthorById(long autId) {
        return authorRepository.findById(autId)
                .orElseThrow(() -> new BookServiceException(format(MSG_AUTHOR_NOT_FOUND, autId)));
    }

    @Override
    public List<Author> getAllAuthors() {
        var allAuthors = authorRepository.findAll();
        if (isEmpty(allAuthors)) {
            throw new BookServiceException(MSG_EMPTY_AUTHOR_TABLE);
        }
        return allAuthors;
    }

    @Override
    public void deleteAuthorById(long autId) {
        authorRepository.delete(getAuthorById(autId));
    }
}