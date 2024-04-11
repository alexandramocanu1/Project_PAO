package exceptions;

public class InvalidDataException extends Exception {
    public InvalidDataException() {
        super("Invalid data provided.");
    }

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataException(Throwable cause) {
        super("Invalid data provided.", cause);
    }
}