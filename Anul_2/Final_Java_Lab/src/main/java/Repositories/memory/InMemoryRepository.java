package Repositories.memory;

import Domain.Entity;
import Domain.Validators.Validator;
import Exceptions.ValidationException;
import Repositories.CrudRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Repository for memory data persistence
 *
 * @param <ID> type of the id of the stored entities
 * @param <E>  type of the stored entities
 */
public class InMemoryRepository<ID, E extends Entity<ID>> implements CrudRepository<ID, E> {
    private Map<ID, E> items;
    private Validator<E> validator;

    public InMemoryRepository(Validator<E> validator) {
        items = new HashMap<>();
        this.validator = validator;
    }

    /**
     * @param id - the id of the entity to be returned
     *           id must not be null
     * @return the entity with the specified id
     * or null - if there is no entity with the given id
     * @throws IllegalArgumentException if id is null.
     */
    @Override
    public E findOne(ID id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null!!!");
        }
        return items.get(id);
    }

    /**
     * @return all entities
     */
    @Override
    public Iterable<E> findAll() {
        return items.values();
    }

    /**
     * @param entity entity must be not null
     * @return null - if the given entity is saved
     * otherwise returns the entity (id already exists)
     * @throws ValidationException      if the entity is not valid
     * @throws IllegalArgumentException if the given entity is null.
     */
    @Override
    public E save(E entity) throws ValidationException, IllegalArgumentException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null!!!");
        }
        validator.validate(entity);
        return items.putIfAbsent(entity.getId(), entity);
    }

    /**
     * removes the entity with the specified id
     *
     * @param id * id must be not null
     * @return the removed entity or null if there is no entity with the given id
     * @throws IllegalArgumentException if the given id is null.
     */
    @Override
    public E delete(ID id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null!!!");
        }
        return items.remove(id);
    }

    /**
     * @param entity entity must not be null
     * @return oldEntity - the previous version of the entity if the entity is updated,
     * otherwise returns null - (e.g id does not exist).
     * @throws IllegalArgumentException if the given entity is null.
     * @throws ValidationException      if the entity is not valid.
     */
    @Override
    public E update(E entity) throws IllegalArgumentException, ValidationException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null!!!");
        }
        validator.validate(entity);
        E oldEntity = items.get(entity.getId());
        if (oldEntity != null) {
            items.replace(entity.getId(), entity);
        }
        return oldEntity;
    }
}