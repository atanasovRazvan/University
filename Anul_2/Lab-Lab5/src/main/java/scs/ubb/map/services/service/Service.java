package scs.ubb.map.services.service;

import scs.ubb.map.domain.Entity;
import scs.ubb.map.repository.CrudRepository;
import scs.ubb.map.validators.ValidationException;

public class Service<ID, E extends Entity<ID>> implements CrudService<ID, E> {
    private CrudRepository<ID, E> repository;

    public Service(CrudRepository<ID, E> repository) {
        this.repository = repository;
    }

    @Override
    public E findOne(ID id) {
        return repository.findOne(id);
    }

    @Override
    public Iterable<E> findAll() {
        return repository.findAll();
    }

    @Override
    public E save(E entity) throws ValidationException {
        return repository.save(entity);
    }

    @Override
    public E delete(ID id) {
        return repository.delete(id);
    }

    @Override
    public E update(E entity) {
        return repository.update(entity);
    }
}
