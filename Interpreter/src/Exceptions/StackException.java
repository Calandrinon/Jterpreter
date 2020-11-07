package Exceptions;

public class StackException extends GeneralException {
    public StackException(String message) {
        super("StackException: " + message);
    }
}
