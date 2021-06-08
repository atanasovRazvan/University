package socialnetwork.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import socialnetwork.domain.Utilizator;
import socialnetwork.service.Service;

public class LoginController {

    public TextField input;
    public TextField idInput;
    public TextField firstNameInput;
    public TextField lastNameInput;

    private Service service;
    private Utilizator user;

    public void setService(Service service) {
        this.service = service;
    }

    private void registerUser() {

        Utilizator user = new Utilizator(firstNameInput.getText(), lastNameInput.getText());
        user.setId(Long.parseLong(idInput.getText()));
        service.addUser(user);

        firstNameInput.setText("");
        lastNameInput.setText("");
        idInput.setText("");

    }

    public void login(ActionEvent actionEvent) {

        if(!idInput.getText().equals("") || !firstNameInput.getText().equals("") || !lastNameInput.getText().equals("")){
            registerUser();
        }
        else {

            try {

                user = service.getUser(Long.parseLong(input.getText()));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/app.fxml"));
                AnchorPane root = loader.load();
                MainWindowController controller = loader.getController();
                controller.setService(service, user);
                Scene scene = new Scene(root);
                Stage window = (Stage) input.getScene().getWindow();
                window.setTitle(user.getFirstName() + " " + user.getLastName() + "'s messenger");
                window.setScene(scene);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
