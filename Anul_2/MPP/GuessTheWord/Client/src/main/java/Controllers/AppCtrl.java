package Controllers;

import Services.IService;
import Services.Observer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AppCtrl extends UnicastRemoteObject implements Observer, Serializable {

    public TextField wordInput;
    public Button submitWord;
    public Label labelPlayer1;
    public Label labelPlayer2;
    public TextField player1Input;
    public TextField player2Input;
    public TextField letterInput;
    public Button submitWholeWord;
    public Button submitLetter;
    private IService server;
    private String user;

    private String player1, player2;

    public AppCtrl() throws RemoteException {
    }

    public void initialize(){

        labelPlayer1.setVisible(false);
        labelPlayer2.setVisible(false);
        player1Input.setVisible(false);
        player2Input.setVisible(false);
        letterInput.setVisible(false);
        submitLetter.setVisible(false);
        submitWholeWord.setVisible(false);

    }

    public void setNeeds(IService server, String username) {
        this.server = server;
        this.user = username;
    }

    @Override
    public void playersReady(String player1, String player2, String word1, String word2) {

        wordInput.setVisible(false);
        submitWord.setVisible(false);
        labelPlayer1.setVisible(true);
        labelPlayer2.setVisible(true);
        player1Input.setVisible(true);
        player2Input.setVisible(true);
        submitLetter.setVisible(true);
        letterInput.setVisible(true);
        submitWholeWord.setVisible(true);

        Platform.runLater(() -> {
            this.player1 = player1;
            this.player2 = player2;
            labelPlayer1.setText(this.player1 + "'s word: " + word1);
            labelPlayer2.setText(this.player2 + "'s word: " + word2);
        });

    }

    @Override
    public void switchRound(String word1, String word2) {
        player1Input.setVisible(true);
        player2Input.setVisible(true);
        submitLetter.setVisible(true);
        letterInput.setVisible(true);
        submitWholeWord.setVisible(true);

        Platform.runLater(() -> {

            labelPlayer1.setText(this.player1 + "'s word: " + word1);
            labelPlayer2.setText(this.player2 + "'s word: " + word2);

        });
    }

    @Override
    public void gameEnded() {
        Platform.runLater(() -> {

            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/results.fxml"));
                AnchorPane root = loader.load();
                ResultsCtrl controller = loader.getController();
                controller.setNeeds(server, user);
                Stage stage = (Stage) submitWord.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(root);
                Stage stage2 = new Stage();
                stage2.setTitle("Game History");
                stage2.setScene(scene);
                stage2.show();
            }
            catch(Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Ending game error: " + ex.getMessage());
                alert.show();
                ex.printStackTrace();
            }

        });
    }

    public void handleSubmitbtn(ActionEvent actionEvent) {

        server.registerWord(this.user, wordInput.getText());
        wordInput.setDisable(true);
        submitWord.setDisable(true);

    }

    public void sendWholeWord(ActionEvent actionEvent) {

        player1Input.setVisible(false);
        player2Input.setVisible(false);
        submitLetter.setVisible(false);
        letterInput.setVisible(false);
        submitWholeWord.setVisible(false);
        if(!player1Input.getText().equals(""))
            server.sendWord(player1Input.getText(), player1, this.user);
        else
            server.sendWord(player1Input.getText(), player2, this.user);

    }

    public void sendLetter(ActionEvent actionEvent) {

        player1Input.setVisible(false);
        player2Input.setVisible(false);
        submitLetter.setVisible(false);
        letterInput.setVisible(false);
        submitWholeWord.setVisible(false);
        server.sendLetter(letterInput.getText(), this.user);

    }
}
