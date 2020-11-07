package Exceptions;

public class UndefinedSymbolException extends GeneralException {
    public UndefinedSymbolException(String message) {
        super("UndefinedSymbolException: " + message);
    }
}
