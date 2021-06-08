package Repository;
import Model.HasID;
import Validator.Validator;
import Validator.ValidationException;
import java.util.*;


/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> - type of entities saved in repository
 */
public abstract class AbstractCRUDRepository<ID , E extends HasID<ID>> implements Repository<ID,E> {
    Map<ID,E> entityes;
    Validator<E> validator;

    /**
     * Constructor for class AbstractCRUDRepository
     * @param v - an instance of Validator
     */
    public AbstractCRUDRepository(Validator v){
        entityes=new HashMap<ID,E>();
        validator=v;
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
        return entityes.get(id);
    }

    /**
     *
     * @return all entities
     */
    @Override
    public Iterable<E> findAll(){
        return entityes.values();
    }

    /**
     *
     * @param entity
     * entity must be not null
     * @return null- if the given entity is saved
     * otherwise returns the entity (id already exists)
     * @throws ValidationException
     * if the entity is not valid
     * @throws IllegalArgumentException
     * if the given entity is null. *
     */
    @Override
    public void save(E entity) throws ValidationException {
        validator.validate(entity);
        entityes.put(entity.getID(),entity);
    }

    /**
     * removes the entity with the specified id
     * @param id
     * id must be not null
     * @return the removed entity or null if there is no entity with the given id
     * @throws IllegalArgumentException
     * if the given id is null.
     */
    @Override
    public void delete(ID id){
        entityes.remove(id);
    }

    /**
     *
     * @param entity
     * entity must not be null
     * @return null - if the entity is updated,
     * otherwise returns the entity - (e.g id does not exist).
     * @throws IllegalArgumentException
     * if the given entity is null.
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
                return entityes.replace(entity.getID(), entity);
            }
        }catch(ValidationException e){
            return null;
        }
    }

    /**
     *
     * @return the number of elements from the repository
     */
    @Override
    public long size(){
        return entityes.size();
    }

}

