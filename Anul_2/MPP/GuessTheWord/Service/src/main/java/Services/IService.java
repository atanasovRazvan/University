package Services;

import Entities.User;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public interface IService {

    boolean userExists(User player);
    void login(User player, Observer controller) throws Exception;
    void registerWord(String player, String word);
    void sendWord(String text, String player, String sender);
    void sendLetter(String text, String sender);
    Map<String, Integer> getScores();
}
