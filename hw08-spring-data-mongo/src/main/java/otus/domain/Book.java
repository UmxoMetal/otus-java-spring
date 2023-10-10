package otus.domain;

import lombok.Data;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Builder
@Document
public class Book {
    @Id
    private String bookId;
    private String bookName;
    @DBRef
    private Author bookAuthor;
    @DBRef
    private List<Genre> bookGenres;
    private List<Comment> bookComments;
    private Short bookRating;
}