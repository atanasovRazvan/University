package Validator;

/**
 *
 * @param <E> - type of entities to validate
 */
public interface Validator<E> {
    /**
     * validates an entity
     * @param entity - type <E>
     * @throws ValidationException
     */
    void validate(E entity) throws ValidationException;
}
