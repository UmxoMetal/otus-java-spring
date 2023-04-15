package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Book;
import ru.otus.exception.BookServiceException;
import ru.otus.service.BookService;

import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static ru.otus.dao.impl.BookDaoImpl.*;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private static final String MSG_BOOK_NOT_FOUND = "Book with id %s not found";
    private static final String MSG_EMPTY_BOOK_TABLE = "Book table is empty";
    private static final String MSG_DELETION_FAILED = "Book deletion by id failed";

    @Override
    @Transactional
    public void createBook(Book book) {
        bookDao.createBook(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(long booId) {
        return bookDao.getBookById(booId)
                .orElseThrow(() -> new BookServiceException(format(MSG_BOOK_NOT_FOUND, booId)));
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookByIdWithGraph(long booId, String graphName) {
        return bookDao.getBookByIdWithGraph(booId, graphName)
                .orElseThrow(() -> new BookServiceException(format(MSG_BOOK_NOT_FOUND, booId)));
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookByIdWithFullInfo(long booId) {
        bookDao.getBookByIdWithGraph(booId, BOOK_AUTHORS_GRAPH)
                .orElseThrow(() -> new BookServiceException(format(MSG_BOOK_NOT_FOUND, booId)));
        bookDao.getBookByIdWithGraph(booId, BOOK_GENRES_GRAPH).get();
        return bookDao.getBookByIdWithGraph(booId, BOOK_COMMENTS_GRAPH).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        var allBooks = bookDao.getAllBooks();
        if (isEmpty(allBooks)) {
            throw new BookServiceException(MSG_EMPTY_BOOK_TABLE);
        }
        return allBooks;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooksWithInfo() {
        var allBooksWithInfo = bookDao.getAllBooksWithGraph(BOOK_AUTHORS_GRAPH);
        if (isEmpty(allBooksWithInfo)) {
            throw new BookServiceException(MSG_EMPTY_BOOK_TABLE);
        }
        bookDao.getAllBooksWithGraph(BOOK_GENRES_GRAPH);
        return bookDao.getAllBooksWithGraph(BOOK_COMMENTS_GRAPH);
    }

    @Override
    @Transactional
    public void deleteBookById(long booId) {
        if (bookDao.deleteBookById(booId) == 0) {
            throw new BookServiceException(MSG_DELETION_FAILED);
        }
    }
}