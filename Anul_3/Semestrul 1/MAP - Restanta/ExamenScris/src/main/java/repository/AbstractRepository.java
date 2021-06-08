package repository;

import model.Entity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSession;

import java.util.List;

public abstract class AbstractRepository<ID, E extends Entity<ID>> implements IRepository<ID, E> {

    private final SessionFactory sessionFactory;

    public AbstractRepository() {
        sessionFactory = HibernateSession.getSessionFactory();
    }

    @Override
    public List<E> findAll(String className) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List result = session.createQuery("select a from " + className + " a")
                    .list();
            session.getTransaction().commit();
            session.close();
            return (List<E>) result;
        }
    }

    public void add(E e){

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(e);
            session.getTransaction().commit();
            session.close();
        }

    }
}
