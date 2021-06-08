package Controllers;

import Entities.User;
import Services.IService;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginCtrl extends UnicastRemoteObject implements Serializable {

    public TextField username;
    public TextField password;
    public Button submitbtn;
    private IService server;

    public LoginCtrl() throws RemoteException {
    }

    public void setServer(IService server) {

        this.server = server;

    }

    public void handleSubmitButton(javafx.event.ActionEvent actionEvent) {

        Platform.runLater(() -> {
            User player = new User(username.getText(), password.getText());
            try{

                if(server.userExists(player)) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/app.fxml"));
                    AnchorPane root = loader.load();
                    AppCtrl controller = loader.getController();
                    server.login(player, controller);
                    controller.setNeeds(server, player.getUsername());
                    Stage stage = (Stage) submitbtn.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(root);
                    Stage stage2 = new Stage();
                    stage2.setTitle(player.getUsername());
                    stage2.setScene(scene);
                    stage2.show();

                }
            }
            catch(Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, "GUI Login error: " + ex.getMessage());
                alert.show();
                ex.printStackTrace();
            }
        });

    }
}
