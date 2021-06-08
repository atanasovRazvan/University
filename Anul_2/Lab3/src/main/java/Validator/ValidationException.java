package Validator;

/**
 * class for a validation exception
 */
public class ValidationException extends Exception {
    /**
     *
     * @param s - string
     */
    public ValidationException(String s){
        super(s);
    }
}
