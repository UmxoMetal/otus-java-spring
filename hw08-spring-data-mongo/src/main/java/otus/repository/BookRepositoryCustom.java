package otus.repository;

public interface BookRepositoryCustom {
    void updateBookRatingById(String booId, Short bookRating);
}