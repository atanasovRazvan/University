package Services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote {
    void playersReady(String player1, String player2, String word1, String word2) throws RemoteException;
    void switchRound(String word1, String word2) throws RemoteException;
    void gameEnded() throws RemoteException;
}
