package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.AuthorDto;
import ru.otus.domain.BookDto;
import ru.otus.domain.GenreDto;
import ru.otus.exception.BookServiceException;
import ru.otus.service.BookService;

import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    private static final String MSG_BOOK_NOT_FOUND = "Book with id %s not found";
    private static final String MSG_EMPTY_BOOK_TABLE = "Book table is empty";
    private static final String MSG_DELETION_FAILED = "Book deletion by id failed";

    @Override
    public void createBook(BookDto bookDto) {
        bookDao.createBook(bookDto);
    }

    @Override
    public BookDto getBookById(Long booId) {
        return bookDao.getBookById(booId)
                .orElseThrow(() -> new BookServiceException(format(MSG_BOOK_NOT_FOUND, booId)));
    }

    @Override
    public BookDto getBookByIdWithFullInfo(Long booId) {
        var bookDto = bookDao.getBookById(booId)
                .orElseThrow(() -> new BookServiceException(format(MSG_BOOK_NOT_FOUND, booId)));
        bookDto.getBookAuthors().addAll(authorDao.getAllAuthorsByBooId(booId));
        bookDto.getBookGenres().addAll(genreDao.getAllGenresByBooId(booId));
        return bookDto;
    }

    @Override
    public List<BookDto> getAllBooks() {
        var allBooks = bookDao.getAllBooks();
        if (isEmpty(allBooks)) {
            throw new BookServiceException(MSG_EMPTY_BOOK_TABLE);
        }
        return allBooks;
    }

    @Override
    public void deleteBookById(Long booId) {
        if (bookDao.deleteBookById(booId) == 0) {
            throw new BookServiceException(MSG_DELETION_FAILED);
        }
    }
}