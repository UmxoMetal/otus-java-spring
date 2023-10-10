package otus.repository;

public interface BookRepositoryCustom {
    void updateBookRatingById(String bookId, Short bookRating);
}