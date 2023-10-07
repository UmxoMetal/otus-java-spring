package otus.service;

import otus.domain.Book;

import java.util.List;

public interface BookService {
    void createBook(Book book);

    Book getBookById(String booId);

    void updateBookRatingById(String booId, Short bookRating);

    Book getBookByBookName(String bookName);

    List<Book> getAllBooks();

    void deleteBookById(String booId);
}