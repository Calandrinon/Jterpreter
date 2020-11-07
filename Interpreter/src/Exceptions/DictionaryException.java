package Exceptions;

public class DictionaryException extends GeneralException {

    public DictionaryException(String message) {
        super("DictionaryException: " + message);
    }
}
