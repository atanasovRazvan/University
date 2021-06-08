package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {

    @FXML private javafx.scene.control.Button cancelButton;

    @FXML
    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleStudentsButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/StudentsView.fxml"));
        loader.getController();
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Student management");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleRaportsButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/RaportsView.fxml"));
        loader.getController();
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Raports");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleAssignmentsButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/AssignmentsView.fxml"));
        loader.getController();
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Assignments management");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleGradesButton(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GradesView.fxml"));
        loader.getController();
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Grades management");
        stage.setScene(scene);
        stage.show();
    }

}
