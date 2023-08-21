package otus.exception;

public class BookServiceException extends RuntimeException {
    public BookServiceException(String message) {
        super(message);
    }
}