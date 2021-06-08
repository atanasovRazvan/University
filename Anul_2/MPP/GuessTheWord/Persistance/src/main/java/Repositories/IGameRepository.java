package Repositories;

import Entities.Game;

import java.util.List;

public interface IGameRepository {

    void add(Game game);
    List<String> getWords(Integer gameid);
    Integer generateId();

}
