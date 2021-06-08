package Services;

import Entities.HasID;

public interface Service<ID, E extends HasID<ID>> {

        E findOne(ID id);
        Iterable<E> findAll();
        E save(E entity);
        E delete(ID id);
        E update(E entity);
        long size();
}
