package repository;

import model.Entity;

import java.util.List;

public interface Repository<E extends Entity> {

    E add(E entity);
    E update(E entity);
    E remove(E entity);
    E findOne(String _id);
    List<E> findAll();

}
