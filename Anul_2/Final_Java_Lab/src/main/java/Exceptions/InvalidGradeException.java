package Exceptions;

public class InvalidGradeException extends ValidationException {
    public InvalidGradeException(String errorMessage) {
        super(errorMessage);
    }
}
