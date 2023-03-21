package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.AuthorDto;
import ru.otus.exception.BookServiceException;
import ru.otus.service.AuthorService;

import java.util.List;

import static java.lang.String.*;
import static org.apache.commons.collections4.CollectionUtils.*;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    private static final String MSG_AUTHOR_NOT_FOUND = "Author with id %s not found";
    private static final String MSG_EMPTY_AUTHOR_TABLE = "Author table is empty";
    private static final String MSG_DELETION_FAILED = "Author deletion by id failed";

    @Override
    public void createAuthor(AuthorDto authorDto) {
        authorDao.createAuthor(authorDto);
    }

    @Override
    public AuthorDto getAuthorById(Long autId) {
        return authorDao.getAuthorById(autId)
                .orElseThrow(() -> new BookServiceException(format(MSG_AUTHOR_NOT_FOUND, autId)));
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        var allAuthors = authorDao.getAllAuthors();
        if (isEmpty(allAuthors)) {
            throw new BookServiceException(MSG_EMPTY_AUTHOR_TABLE);
        }
        return allAuthors;
    }

    @Override
    public void deleteAuthorById(Long autId) {
        if (authorDao.deleteAuthorById(autId) == 0) {
            throw new BookServiceException(MSG_DELETION_FAILED);
        }
    }
}