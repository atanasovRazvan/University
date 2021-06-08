package repository;

import model.Entity;

import java.util.List;

public interface IRepository <ID, E extends Entity<ID>> {

    E findOne(ID id);
    List<E> findAll();
    E save(E entity);

}
