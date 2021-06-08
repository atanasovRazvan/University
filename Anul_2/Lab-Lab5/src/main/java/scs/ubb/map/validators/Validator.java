package scs.ubb.map.validators;

public interface Validator<E> {
    void validate(E entity) throws ValidationException;
}

