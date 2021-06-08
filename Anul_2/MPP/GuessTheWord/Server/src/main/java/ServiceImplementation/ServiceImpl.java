package ServiceImplementation;

import Entities.Game;
import Entities.Round;
import Entities.User;
import Repositories.GameRepository;
import Repositories.RoundRepository;
import Repositories.UserRepository;
import Services.IService;
import Services.Observer;
import javafx.util.Pair;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceImpl implements IService, Remote {

    private UserRepository userRepository;
    private GameRepository gameRepository;
    private RoundRepository roundRepository;
    private Map<String, Observer> loggedClients;
    private Map<String, String> players;
    private Integer noPlayersReady;
    private Integer gameId;
    private Integer round;
    private Integer noAnswers;
    private List<String> answers;
    private List<Pair<String, String>> submits;

    public ServiceImpl(UserRepository userRepository, GameRepository gameRepository, RoundRepository roundRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.roundRepository = roundRepository;
        this.loggedClients = new ConcurrentHashMap<>();
        this.players = new HashMap<>();
        this.noPlayersReady = 0;
        this.round = 1;
        this.noAnswers = 0;
        this.answers = new ArrayList<>();
        submits = new ArrayList<>();
    }

    @Override
    public boolean userExists(User user) {
        return userRepository.findOne(user);
    }

    @Override
    public void login(User user, Observer observer) throws Exception {

        System.out.println("Am intrat in login zic");
        if(userRepository.findOne(user)){
            if(loggedClients.get(user.getUsername())!=null)
                throw new Exception("User already logged in");
            loggedClients.put(user.getUsername(), observer);
            players.put(user.getUsername(), "");

        }
        else{
            throw new Exception("Authentication failed");
        }

    }

    @Override
    public void registerWord(String playerName, String word) {
        this.noPlayersReady++;
        players.put(playerName, word);
        if(this.noPlayersReady == 3){

            this.gameId = gameRepository.generateId();
            for(Map.Entry<String, String> entry : players.entrySet()){
                this.answers.add(entry.getValue());
            }
            Game game = new Game(answers.get(0), answers.get(1), answers.get(2), gameId);
            gameRepository.add(game);
            int defaultThreadsNo = 3;
            ExecutorService executor = Executors.newFixedThreadPool(defaultThreadsNo);
            for(Map.Entry<String, Observer> entry : loggedClients.entrySet()){
                executor.execute(() -> {
                    try{
                        Observer player = entry.getValue();

                        List<String> playerz = new ArrayList<>();
                        List<String> wordz = new ArrayList<>();
                        for(Map.Entry<String, String> entry_ : this.players.entrySet()){
                            if(entry_.getKey().equals(entry.getKey())) continue;
                            playerz.add(entry_.getKey());
                            wordz.add(entry_.getValue());
                        }

                        StringBuilder word1 = new StringBuilder();
                        for(int i = 0; i < wordz.get(0).length(); i++)
                            word1.append("_");
                        StringBuilder word2 = new StringBuilder();
                        for(int i = 0; i < wordz.get(1).length(); i++)
                            word2.append("_");

                        player.playersReady(playerz.get(0), playerz.get(1), word1.toString(), word2.toString());
                    }
                    catch(Exception e){
                        System.err.println("Error initiating app");
                        e.printStackTrace();
                    }
                });
            }
            executor.shutdown();
        }
    }

    @Override
    public void sendWord(String text, String player, String sender) {
        this.noAnswers++;
        Integer score = 0;

        for(Map.Entry<String, String> entry_ : this.players.entrySet())
            if(entry_.getKey().equals(player) && entry_.getValue().equals(text))
                score+=10;

        Round round = new Round(sender, text, this.round, this.gameId, score);
        roundRepository.add(round);
        submits.add(new Pair<>(sender, text));

        if(this.noAnswers % 3 == 0) switchRound();
    }

    @Override
    public void sendLetter(String text, String sender) {
        this.noAnswers++;
        Integer score = 0;

        List<String> words = new ArrayList<>();
        for(Map.Entry<String, String> entry_ : this.players.entrySet()){
            if(entry_.getKey().equals(sender)) continue;
            words.add(entry_.getValue());
        }

        for(int i = 0; i < words.get(0).length(); i++)
            if(words.get(0).charAt(i) == text.charAt(0))
                score++;
        for(int i = 0; i < words.get(1).length(); i++)
            if(words.get(1).charAt(i) == text.charAt(0))
                score++;

        Round round = new Round(sender, text, this.round, this.gameId, score);
        roundRepository.add(round);
        submits.add(new Pair<>(sender, text));

        if(this.noAnswers % 3 == 0) switchRound();
    }

    @Override
    public Map<String, Integer> getScores() {
        return roundRepository.getScores(this.gameId);
    }

    private void switchRound() {

        this.round++;
        if(this.round == 4)
            endGame();

        int defaultThreadsNo = 3;
        ExecutorService executor = Executors.newFixedThreadPool(defaultThreadsNo);
        for(Map.Entry<String, Observer> entry : loggedClients.entrySet()){
            executor.execute(() -> {
                try {
                    Observer player = entry.getValue();
                    List<String> words = new ArrayList<>();
                    for (int i = 0; i < submits.size(); i++)
                        if (submits.get(i).getKey().equals(entry.getKey())) {

                            if (submits.get(i).getValue().length() == 1) {

                                for (int j = 1; j < answers.size(); j++) {
                                    if (j == i) continue;
                                    StringBuilder word1 = new StringBuilder();
                                    for (int ii = 0; ii < this.answers.get(j).length(); ii++)
                                        if (submits.get(i).getValue().charAt(0) == this.answers.get(j).charAt(ii))
                                            word1.append(submits.get(i).getValue().charAt(0));
                                        else
                                            word1.append("_");
                                    words.add(word1.toString());

                                }

                            } else {

                                for (int j = 1; j < answers.size(); j++) {
                                    if (j == i) continue;
                                    if (this.answers.get(j).equals(submits.get(i).getValue()))
                                        words.add(this.answers.get(j));
                                    else {
                                        StringBuilder word = new StringBuilder();
                                        for (int ii = 0; ii < this.answers.get(j).length(); ii++)
                                            word.append("_");
                                        words.add(word.toString());
                                    }

                                }

                            }

                            player.switchRound(words.get(0), words.get(1));
                        }
                }
                catch(Exception e){
                    System.err.println("Error going to next round");
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();

    }

    private void endGame(){

        int defaultThreadsNo = 3;
        ExecutorService executor = Executors.newFixedThreadPool(defaultThreadsNo);
        for(Map.Entry<String, Observer> entry : loggedClients.entrySet()){
            executor.execute(() -> {
                try{
                    Observer player = entry.getValue();
                    player.gameEnded();
                }
                catch(Exception e){
                    System.err.println("Error ending game");
                }
            });
        }
        executor.shutdown();

    }

}
