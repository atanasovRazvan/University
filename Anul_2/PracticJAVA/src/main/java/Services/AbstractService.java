package Services;

import Entities.HasID;
import Repositories.AbstractRepository;

import java.util.List;

public abstract class AbstractService<ID, E extends HasID<ID>> implements Service<ID, E> {

    private AbstractRepository repo;

    AbstractService(AbstractRepository reposit){

        repo = reposit;

    }

    public E findOne(ID id){
        return (E) repo.findOne(id);
    }

    public List<E> findAll(){
        return repo.findAll();
    }

    public E save(E Entity){
        return (E) repo.save(Entity);
    }

    public E delete(ID id){
        return (E) repo.delete(id);
    }

    public E update(E Entity){
        return (E) repo.update(Entity);
    }

    public long size(){
        return repo.size();
    }

    public void show(){
        repo.findAll().forEach(System.out::println);
    }
}
