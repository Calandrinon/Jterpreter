package Exceptions;

public class GeneralException extends RuntimeException {
    public GeneralException(String message) {
        super("GeneralException: " + message);
    }
}
