package Exceptions;

public class FileException extends GeneralException {
    public FileException(String message) {
        super("FileException: " + message);
    }
}
