package otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.domain.Book;
import otus.exception.BookServiceException;
import otus.repository.BookRepository;
import otus.service.BookService;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.data.domain.Sort.*;
import static org.springframework.data.domain.Sort.Order.*;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private static final String MSG_BOOK_NOT_FOUND = "Book with id %s not found";
    private static final String MSG_EMPTY_BOOK_TABLE = "Book table is empty";

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(String bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookServiceException(format(MSG_BOOK_NOT_FOUND, bookId)));
    }

    @Override
    public void updateBookRatingById(String bookId, Short bookRating) {
        bookRepository.updateBookRatingById(bookId, bookRating);
    }

    @Override
    public Book getBookByBookName(String bookName) {
        return bookRepository.getBooksByBookName(bookName)
                .stream()
                .findFirst()
                .orElseThrow(() -> new BookServiceException("Book not found"));
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
    public List<Book> getAllBooksSortedAsc() {
        var allBooks = bookRepository.findAll(by(asc("bookName")));
        if (isEmpty(allBooks)) {
            throw new BookServiceException(MSG_EMPTY_BOOK_TABLE);
        }
        return allBooks;
    }

    @Override
    public List<Book> getAllBooksSortedDesc() {
        var allBooks = bookRepository.findAllByOrderByBookNameDesc();
        if (isEmpty(allBooks)) {
            throw new BookServiceException(MSG_EMPTY_BOOK_TABLE);
        }
        return allBooks;
    }

    @Override
    public void deleteBookById(String bookId) {
        bookRepository.delete(getBookById(bookId));
    }
}