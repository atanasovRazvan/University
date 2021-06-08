package repository;

import model.Entity;
import model.Item;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSession;

import java.util.List;

public class UserRepository implements Repository<User>{

    private final SessionFactory sessionFactory;

    public UserRepository(){
        sessionFactory = HibernateSession.getSessionFactory();
    }

    @Override
    public User add(User entity) {
        return null;
    }

    @Override
    public User update(User entity) {

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            session.close();
        }
        return null;
    }

    @Override
    public User remove(User entity) {
        return null;
    }

    @Override
    public User findOne(String username) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<User> result = session.createQuery("select a from User a where username=:username")
                    .setParameter("username", username)
                    .list();
            session.getTransaction().commit();
            session.close();
            if(result.size() == 1)
                return result.get(0);
            else
                return null;
        }
    }

    @Override
    public List<User> findAll() {

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<User> result = session.createQuery("select a from User a")
                    .list();
            session.getTransaction().commit();
            session.close();
            return result;
        }

    }
}
