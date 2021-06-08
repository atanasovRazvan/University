package Repositories;

import Entities.HasID;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<ID, E extends HasID<ID>> implements Repository<ID, E> {

    private Map<ID, E> entities;
    private String fileName;

    public abstract void loadData() throws IOException;
    public abstract void writeToFile() throws FileNotFoundException;

    public AbstractRepository(String filename) throws FileNotFoundException {
        entities = new HashMap<ID, E>();
        fileName = filename;
        loadData();
    }

    @Override
    public E findOne(ID id){

        if(!entities.containsKey(id)) return null;
        return entities.get(id);
    }

    @Override
    public List<E> findAll(){

        List<E> l = new ArrayList<>();
        l.addAll(entities.values());
        return l;
    }

    @Override
    public E save(E entity) throws FileNotFoundException {
        if(this.findOne(entity.getID()) != null) return entity;
        if(this.findOne(entity.getID()) != null && entity.getID() == this.findOne(entity.getID()).getID()) return entity;
        entities.put(entity.getID(),entity);
        writeToFile();
        return null;
    }

    @Override
    public E delete(ID id) throws FileNotFoundException {
        E entity = this.findOne(id);
        entities.remove(id);
        writeToFile();
        return entity;
    }

    @Override
    public E update(E entity) throws FileNotFoundException {
        if (findOne(entity.getID()) == null) {
            return null;
        } else {
            E ret = entities.replace(entity.getID(), entity);
            writeToFile();
            return ret;
        }
    }

    @Override
    public long size(){
        return entities.size();
    }

}
