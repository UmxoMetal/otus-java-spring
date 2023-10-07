package otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.domain.Book;
import otus.exception.BookServiceException;
import otus.repository.BookRepository;
import otus.service.BookService;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private static final String MSG_BOOK_NOT_FOUND = "Book with id %s not found";
    private static final String MSG_EMPTY_BOOK_TABLE = "Book table is empty";

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book getBookById(String booId) {
        return bookRepository.findById(booId)
                .orElseThrow(() -> new BookServiceException(format(MSG_BOOK_NOT_FOUND, booId)));
    }

    @Override
    public void updateBookRatingById(String booId, Short bookRating) {
        bookRepository.updateBookRatingById(booId, bookRating);
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
    public void deleteBookById(String booId) {
        bookRepository.delete(getBookById(booId));
    }
}