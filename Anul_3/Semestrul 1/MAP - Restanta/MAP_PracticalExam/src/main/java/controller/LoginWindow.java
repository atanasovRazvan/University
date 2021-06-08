package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class LoginWindow {

    public Button submitBtn;
    public TextField usernameTextField;

    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    public void handleSubmitButton(ActionEvent actionEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/mainwindow.fxml"));
        AnchorPane root = null;
        try {
            root = loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        MainWindowController controller = loader.getController();
        controller.setNeeds(service, usernameTextField.getText());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        try {
            stage.setTitle(service.getClientName(usernameTextField.getText()));
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No user found!");
            alert.show();
        }

    }
}
