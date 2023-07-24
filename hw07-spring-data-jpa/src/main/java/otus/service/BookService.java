package otus.service;

import otus.domain.Book;

import java.util.List;

public interface BookService {
    void createBook(Book book);

    Book getBookById(long booId);

    Book getBookByIdWithAuthors(long booId);

    Book getBookByIdWithGenres(long booId);

    Book getBookByIdWithComments(long booId);

    Book getBookByIdWithFullInfo(long booId);

    List<Book> getAllBooks();

    List<Book> getAllBooksWithInfo();

    void deleteBookById(long booId);
}