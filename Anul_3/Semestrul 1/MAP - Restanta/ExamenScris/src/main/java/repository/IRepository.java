package repository;

import model.Entity;

import java.util.List;

public interface IRepository<ID, E extends Entity<ID>> {

    List<E> findAll(String className);

}
