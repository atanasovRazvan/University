package repository;

import model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSession;

import java.util.List;

public class ItemRepository implements Repository<Item>{

    private final SessionFactory sessionFactory;

    public ItemRepository(){
        sessionFactory = HibernateSession.getSessionFactory();
    }

    @Override
    public Item add(Item entity) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            if(findOne(entity.getId()) != null){
                session.close();
                return findOne(entity.getId());
            }
            session.save(entity);
            session.getTransaction().commit();
            session.close();
            return entity;
        }
    }

    @Override
    public Item update(Item entity){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            session.close();
            return entity;
        }
    }

    @Override
    public Item remove(Item entity) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            session.close();
            return entity;
        }
    }

    @Override
    public Item findOne(String _id) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Item> result = session.createQuery("select a from Item a where id=:id")
                    .setParameter("id", _id)
                    .list();
            session.getTransaction().commit();
            session.close();
            if(result.size()>0)
                return result.get(0);
            else
                return null;
        }
    }

    @Override
    public List<Item> findAll() {

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List result = session.createQuery("select a from Item a")
                    .list();
            session.getTransaction().commit();
            session.close();
            return (List<Item>) result;
        }

    }
}
