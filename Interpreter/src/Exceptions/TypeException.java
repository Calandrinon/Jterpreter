package Exceptions;

public class TypeException extends GeneralException {
    public TypeException(String message) {
        super("TypeException: " + message);
    }
}
