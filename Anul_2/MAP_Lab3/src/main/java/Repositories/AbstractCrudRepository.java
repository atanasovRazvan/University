package Repositories;
import Entities.*;
import Validators.*;

import java.util.*;

/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> - type of entities saved in repository
 */
public abstract class AbstractCrudRepository<ID, E extends Entity<ID>> implements CrudRepository<ID, E> {

    Map<ID, E> entities;
    Validator<E> validator;

    /**
     * Constructor for class AbstactCrudRepository
     * @param v - an instance of Validator
     */
    public AbstractCrudRepository(Validator v){
        entities = new HashMap<ID, E>();
        validator = v;
    }

    /**
     *
     * @param id -the id of the entity to be returned
     * id must not be null
     * @return the entity with the specified id
     * or null - if there is no entity with the given id
     * @throws IllegalArgumentException
     * if id is null.
     */
    @Override
    public E findOne(ID id){
        if(!entities.containsKey(id)) return null;
        return entities.get(id);
    }

    /**
     *
     * @return all entities
     */
    @Override
    public List<E> findAll(){

        List<E> l = new ArrayList<>();
        l.addAll(entities.values());
        return l;
    }

    public List<E> getAll(){

        return new ArrayList<E>(entities.values());

    }

    /**
     *
     * @param entity
     * entity must be not null
     * @return null- if the given entity is saved
     * otherwise returns the entity (id already exists)
     * @throws ValidationException
     * if the entity is not valid
     */
    @Override
    public E save(E entity) throws ValidationException {
        validator.validate(entity);
        if(this.findOne(entity.getID()) != null) return entity;
        if(this.findOne(entity.getID()) != null && entity.getID() == this.findOne(entity.getID()).getID()) return entity;
        entities.put(entity.getID(),entity);
        return null;
    }

    /**
     * removes the entity with the specified id
     * @param id
     * id must be not null
     * @return the removed entity or null if there is no entity with the given id
     */
    @Override
    public E delete(ID id){
        E entity = this.findOne(id);
        entities.remove(id);
        return entity;
    }

    /**
     *
     * @param entity
     * entity must not be null
     * @return null - if the entity is updated,
     * otherwise returns the entity - (e.g id does not exist).
     * @throws ValidationException
     * if the entity is not valid.
     */
    @Override
    public E update(E entity){
        try {
            validator.validate(entity);
            if (findOne(entity.getID()) == null) {
                return null;
            } else {
                return entities.replace(entity.getID(), entity);
            }
        }
        catch(ValidationException e){
            return null;
        }
    }

    /**
     *
     * @return the number of elements from the repository
     */
    @Override
    public long size(){
        return entities.size();
    }

}
