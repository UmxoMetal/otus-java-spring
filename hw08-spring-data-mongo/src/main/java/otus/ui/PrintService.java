package otus.ui;

import otus.domain.Book;
import otus.domain.Comment;

public interface PrintService {
    String print(Book book);

    String print(Comment comment);
}