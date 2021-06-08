package validators;

import javax.xml.bind.ValidationException;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}
