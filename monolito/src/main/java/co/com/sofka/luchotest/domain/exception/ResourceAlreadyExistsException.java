package co.com.sofka.luchotest.domain.exception;

/**
 * Domain exception thrown when attempting to create a resource that already exists.
 */
public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
