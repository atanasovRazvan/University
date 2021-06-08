package Repositories;

import Entities.Game;
import Utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class GameRepository implements IGameRepository{
    @Override
    public void add(Game game) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(game);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public List<String> getWords(Integer gameid) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        List<String> words = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            List result = session.createQuery("select a from Game a where gameid=:gameid")
                    .setParameter("gameid", gameid)
                    .list();
            Game o = (Game) result.get(0);
            words.add(((Game) o).getFirstPlayerWord());
            words.add(((Game) o).getSecondPlayerWord());
            words.add(((Game) o).getThirdPlayerWord());
            session.close();
        }
        return words;
    }

    @Override
    public Integer generateId() {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Integer id = 0;
        try(Session session = sessionFactory.openSession()){
            id = (Integer) session.createQuery("select max(game.gameid) from Game game")
                    .list().get(0);
            session.close();
        }
        return id+1;
    }
}
