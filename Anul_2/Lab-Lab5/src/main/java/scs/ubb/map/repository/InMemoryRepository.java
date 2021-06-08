package scs.ubb.map.repository;

import scs.ubb.map.domain.Entity;
import scs.ubb.map.validators.ValidationException;
import scs.ubb.map.validators.Validator;

import java.util.Map;
import java.util.TreeMap;

public class InMemoryRepository<ID, E extends Entity<ID>> implements CrudRepository<ID, E> {
    private Map<ID, E> entities = new TreeMap<>();
    private Validator<E> validator;


    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
    }

    /**
     * @param id -the id of the entity to be returned
     *           id must not be null
     * @return the entity with the specified id
     * or null - if there is no entity with the given id
     * @throws IllegalArgumentException if id is null.
     */
    @Override
    public E findOne(ID id) {
        if (id == null)
            throw new IllegalArgumentException("Id incorect");
        if (entities.containsKey(id))
            return entities.get(id);
        return null;
    }

    @Override
    public Iterable<E> findAll() {
        return (Iterable<E>) entities.values();
    }

    /**
     * @param entity entity must be not null
     * @return null- if the given entity is saved
     * otherwise returns the entity (id already exists)
     * @throws ValidationException      if the entity is not valid
     * @throws IllegalArgumentException if the given entity is null. *
     */
    @Override
    public E save(E entity) throws ValidationException {

        if (entity == null)
            throw new IllegalArgumentException("Entitate inexistenta!");
        E oldValue = entities.get(entity.getId());

        if (oldValue == null) {
            validator.validate(entity);
            oldValue = entities.put(entity.getId(), entity);
        }

        return oldValue;
    }

    /**
     * removes the entity with the specified id
     *
     * @param id id must be not null
     * @return the removed entity or null if there is no entity with the
     * given id
     * @throws IllegalArgumentException if the given id is null.
     */
    @Override
    public E delete(ID id) {
        if (id == null)
            throw new IllegalArgumentException("Id incorect!");
        if (entities.containsKey(id)) {
            E entity = entities.get(id);
            entities.remove(id, entities.get(id));
            return entity;
        } else
            return null;
    }

    /**
     * @param entity entity must not be null
     * @return null - if the entity is updated,
     * otherwise returns the entity - (e.g id does not exist).
     * @throws IllegalArgumentException if the given entity is null.
     * @throws ValidationException      if the entity is not valid.
     */
    @Override
    public E update(E entity) throws ValidationException {
        if (entity == null)
            throw new IllegalArgumentException("Entitate inexistenta!");
        validator.validate(entity);
        if (entities.containsKey(entity.getId())) {
            entities.replace(entity.getId(), entity);
            return null;
        }
        return entity;
    }

}
