package repository;

import model.Entity;
import validators.Validator;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryRepository<ID, E extends Entity<ID>> implements IRepository<ID,E> {

    private Validator<E> validator;
    Map<ID,E> entities;

    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities=new HashMap<ID,E>();
    }

    @Override
    public E findOne(ID id){
        if (id==null)
            throw new RepositoryException("ID can't be null");
        return entities.get(id);
    }

    @Override
    public List<E> findAll() {
        List<E> list = new ArrayList<E>(entities.values());
        return list;
    }

    @Override
    public E save(E entity) {

        if (entity==null)
            throw new RepositoryException("Entity can't be null");
        try {
            validator.validate(entity);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        if(entities.get(entity.getId()) != null) {
            entities.put(entity.getId(), entity);
            return entity;
        }
        else entities.put(entity.getId(),entity);
        return null;
    }

}
