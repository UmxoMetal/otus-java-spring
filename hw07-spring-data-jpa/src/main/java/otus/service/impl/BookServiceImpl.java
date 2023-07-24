package otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.domain.Book;
import otus.exception.BookServiceException;
import otus.repository.BookRepository;
import otus.service.BookService;

import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private static final String MSG_BOOK_NOT_FOUND = "Book with id %s not found";
    private static final String MSG_EMPTY_BOOK_TABLE = "Book table is empty";

    public static final String BOOK_AUTHORS_GRAPH = "Book.bookAuthors";
    public static final String BOOK_GENRES_GRAPH = "Book.bookGenres";
    public static final String BOOK_COMMENTS_GRAPH = "Book.bookComments";

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book getBookById(long booId) {
        return bookRepository.findById(booId)
                .orElseThrow(() -> new BookServiceException(format(MSG_BOOK_NOT_FOUND, booId)));
    }

    @Override
    public Book getBookByIdWithAuthors(long booId) {
        return bookRepository.findBookWithAuthorsByBooId(booId)
                .orElseThrow(() -> new BookServiceException(format(MSG_BOOK_NOT_FOUND, booId)));
    }

    @Override
    public Book getBookByIdWithGenres(long booId) {
        return bookRepository.findBookWithGenresByBooId(booId)
                .orElseThrow(() -> new BookServiceException(format(MSG_BOOK_NOT_FOUND, booId)));
    }

    @Override
    public Book getBookByIdWithComments(long booId) {
        return bookRepository.findBookWithCommentsByBooId(booId)
                .orElseThrow(() -> new BookServiceException(format(MSG_BOOK_NOT_FOUND, booId)));
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookByIdWithFullInfo(long booId) {
        bookRepository.findBookWithAuthorsByBooId(booId).
                orElseThrow(() -> new BookServiceException(format(MSG_BOOK_NOT_FOUND, booId)));
        bookRepository.findBookWithGenresByBooId(booId).get();
        return bookRepository.findBookWithCommentsByBooId(booId).get();
    }

    @Override
    public List<Book> getAllBooks() {
        var allBooks = bookRepository.findAll();
        if (isEmpty(allBooks)) {
            throw new BookServiceException(MSG_EMPTY_BOOK_TABLE);
        }
        return allBooks;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooksWithInfo() {
        var booksWithAuthors = bookRepository.findBooksWithAuthors();
        System.out.println(booksWithAuthors.size());
        if (isEmpty(booksWithAuthors)) {
            throw new BookServiceException(MSG_EMPTY_BOOK_TABLE);
        }
        List<Book> booksWithGenres = bookRepository.findBooksWithGenres();
        System.out.println(booksWithGenres.size());
        List<Book> bookWithComments = bookRepository.findBookWithComments();
        System.out.println(bookWithComments.size());
        return bookWithComments;
    }

    @Override
    public void deleteBookById(long booId) {
        bookRepository.delete(getBookById(booId));
    }
}