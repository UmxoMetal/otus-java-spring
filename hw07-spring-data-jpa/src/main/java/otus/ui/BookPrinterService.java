package otus.ui;


import otus.domain.Book;

public interface BookPrinterService {
    String print(Book book);

    String printWithAuthors(Book book);

    String printWithGenres(Book book);

    String printWithComments(Book book);

    String printWithFullInfo(Book book);
}