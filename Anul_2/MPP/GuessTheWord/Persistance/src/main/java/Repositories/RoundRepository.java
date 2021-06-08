package Repositories;

import Entities.Round;
import Utils.HibernateSession;
import javafx.util.Pair;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundRepository implements IRoundRepository {
    @Override
    public void add(Round round) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(round);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public List<Pair<String, Integer>> getAnswersAndScores(String player, Integer gameid) {
        List<Pair<String, Integer>> list = new ArrayList<>();
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            List result = session.createQuery("select a from Round a where player=:name and gameid=:id")
                .setParameter("name", player)
                .setParameter("id", gameid)
                .list();
            for(Object o : result) {
                Pair<String, Integer> pair = new Pair(((Round) o).getAnswer(), ((Round) o).getScore());
                list.add(pair);
            }
        }
        return list;
    }

    @Override
    public Map<String, Integer> getScores(Integer gameid) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            List result = session.createQuery("select a from Round a where gameid=:id")
                    .setParameter("id", gameid)
                    .list();
            Map<String, Integer> map = new HashMap<>();
            for(Object o : result){
                Round round = (Round) o;
                map.put(round.getPlayerName(), map.get(round.getPlayerName())+round.getScore());
            }
            return map;
        }
    }
}
