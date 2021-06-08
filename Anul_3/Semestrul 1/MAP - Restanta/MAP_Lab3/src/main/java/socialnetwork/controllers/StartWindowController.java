package socialnetwork.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import socialnetwork.service.Service;

import java.io.IOException;

public class StartWindowController {

    private Service service;

    public void setService(Service service) {
        this.service = service;
    }


    public void buttonClicked(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/loginscene.fxml"));
            AnchorPane root = loader.load();
            LoginController controller = loader.getController();
            controller.setService(service);
            Scene scene = new Scene(root);
            Stage mainWindow = new Stage();
            mainWindow.setTitle("Login");
            mainWindow.setScene(scene);
            mainWindow.show();
        }
        catch (IOException exception) {
                exception.printStackTrace();
            }

    }
}
