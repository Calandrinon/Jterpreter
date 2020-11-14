package Exceptions;

public class HeapException extends GeneralException {
    public HeapException(String message) {
        super("HeapException: " + message);
    }
}
