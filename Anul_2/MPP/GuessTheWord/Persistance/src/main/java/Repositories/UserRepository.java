package Repositories;

import Entities.User;
import Utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserRepository implements IUserRepository{
    @Override
    public Boolean findOne(User user) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            List result = session.createQuery("select a from User a where username=:username and password=:password")
                    .setParameter("username", user.getUsername())
                    .setParameter("password", user.getPassword())
                    .list();
            session.close();
            return result.size() == 1;
        }
    }
}
