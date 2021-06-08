package Controllers;

import Services.IService;
import javafx.scene.control.Label;
import javafx.util.Pair;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultsCtrl extends UnicastRemoteObject  {

    public Label player1, player2, player3, score1, score2, score3;
    private IService server;
    private String player;

    public ResultsCtrl() throws RemoteException {
    }

    public void setNeeds(IService server, String player) {
        this.server = server;
        this.player = player;
        this.initialize_();
    }

    private void initialize_() {

        Map<String, Integer> list = server.getScores();
        List<Pair<String, Integer>> scores = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : list.entrySet())
            scores.add(new Pair<>(entry.getKey(), entry.getValue()));


        player1.setText(scores.get(0).getKey());
        player2.setText(scores.get(1).getKey());
        player3.setText(scores.get(2).getKey());

        score1.setText(scores.get(0).getValue().toString());
        score2.setText(scores.get(1).getValue().toString());
        score3.setText(scores.get(2).getValue().toString());

    }


}
