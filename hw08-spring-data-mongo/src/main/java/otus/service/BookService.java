package otus.service;

import otus.domain.Book;

import java.util.List;

public interface BookService {
    Book create(Book book);

    Book getBookById(String bookId);

    void updateBookRatingById(String bookId, Short bookRating);

    Book getBookByBookName(String bookName);

    List<Book> getAllBooks();

    List<Book> getAllBooksSortedAsc();

    List<Book> getAllBooksSortedDesc();

    void deleteBookById(String bookId);
}