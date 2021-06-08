package Service;

import Entities.Entity;
import Repositories.AbstractCrudRepository;
import Validators.ValidationException;

import java.util.List;

public abstract class AbstractService<ID, E extends Entity<ID>> implements Service<ID, E> {

    private AbstractCrudRepository repo;

    AbstractService(AbstractCrudRepository reposit){

        repo = reposit;

    }

    /**
     * @param id -the id of the entity to be returned
     * id must not be null
     * @return Entity
     */
    public E findOne(ID id){
        return (E) repo.findOne(id);
    }

    public List getAll(){
        return repo.getAll();
    }

    /**
     * returns an iterator for all the elements
     * @return Iterable
     */
    public List<E> findAll(){
        return repo.findAll();
    }

    /**
     * Saves an entity in repo
     * @param Entity - the entity to be saved
     * @return the Entity saved
     * @throws ValidationException
     */
    public E save(E Entity) throws ValidationException{
        return (E) repo.save(Entity);
    }

    /**
     * Deletes the entity given by ID
     * @param id - the ID of the entity to be removed
     * id must be not null
     * @return the entity removed
     */
    public E delete(ID id){
        return (E) repo.delete(id);
    }

    /**
     * Changes the entity given by an ID
     * @param Entity - the entity to be updated
     * @return the entity updated
     */
    public E update(E Entity){
        return (E) repo.update(Entity);
    }

    /**
     * Returns the size of the repository
     * @return long
     */
    public long size(){
        return repo.size();
    }

    /**
     * Prints all the elements in the repo
     */
    public void show(){

        repo.findAll().forEach(System.out::println);

    }

}
