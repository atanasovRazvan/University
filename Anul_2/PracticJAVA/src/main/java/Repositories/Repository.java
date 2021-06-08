package Repositories;

import Entities.HasID;

import java.io.FileNotFoundException;

public interface Repository<ID, E extends HasID<ID>> {

    E findOne(ID id);
    Iterable<E> findAll();
    E save(E entity) throws FileNotFoundException;
    E delete(ID id) throws FileNotFoundException;
    E update(E entity) throws FileNotFoundException;
    long size();

}
