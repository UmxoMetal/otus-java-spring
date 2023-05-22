package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.Author;
import ru.otus.exception.BookServiceException;
import ru.otus.service.AuthorService;

import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.*;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    private static final String MSG_AUTHOR_NOT_FOUND = "Author with id %s not found";
    private static final String MSG_EMPTY_AUTHOR_TABLE = "Author table is empty";

    @Override
    @Transactional
    public void createAuthor(Author author) {
        authorDao.create(author);
    }

    @Override
    public Author getAuthorById(long autId) {
        return authorDao.getById(autId)
                .orElseThrow(() -> new BookServiceException(format(MSG_AUTHOR_NOT_FOUND, autId)));
    }

    @Override
    public List<Author> getAllAuthors() {
        var allAuthors = authorDao.getAll();
        if (isEmpty(allAuthors)) {
            throw new BookServiceException(MSG_EMPTY_AUTHOR_TABLE);
        }
        return allAuthors;
    }

    @Override
    @Transactional
    public void deleteAuthorById(long autId) {
        authorDao.delete(getAuthorById(autId));
    }
}