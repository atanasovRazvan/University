package Repositories;

import Entities.Round;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public interface IRoundRepository {

    void add(Round round);
    List<Pair<String, Integer>> getAnswersAndScores(String player, Integer gameid);

    Map<String, Integer> getScores(Integer gameid);
}
