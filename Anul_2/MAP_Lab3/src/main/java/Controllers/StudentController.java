package Controllers;

import Entities.Student;
import Repositories.StudentXMLRepository;
import Service.StudentXMLService;
import Validators.ValidatorStudent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class StudentController {

    @FXML
    private javafx.scene.control.Button cancelButton;
    @FXML
    private TableView table;
    @FXML
    private TableColumn idCol, numeCol, grupaCol, emailCol, profCol;
    @FXML
    private TextField idTextField, numeTextField, grupaTextField, emailTextField, profTextField;
    private ValidatorStudent v = new ValidatorStudent();
    private StudentXMLRepository repo = new StudentXMLRepository(v);
    private StudentXMLService srv = new StudentXMLService(repo);

    @FXML
    public void initialize(){
        loadTableData();
    }

    @FXML
    public void loadTableData(){
        idCol.setCellValueFactory(new PropertyValueFactory("ID"));
        numeCol.setCellValueFactory(new PropertyValueFactory("nume"));
        grupaCol.setCellValueFactory(new PropertyValueFactory("grupa"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        profCol.setCellValueFactory(new PropertyValueFactory("profesor"));
        List list = new ArrayList();
        srv.findAll().forEach(list::add);
        ObservableList data = FXCollections.observableList(list);
        table.setItems(data);
    }

    @FXML
    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleAddButton(ActionEvent actionEvent){
        int idStudent, grupa;
        String nume, email, prof;
        try {
            //if(idTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un ID");
            //if(numeTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un nume");
            //if(grupaTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti o grupa");
            //if(emailTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un email valid");
            //if(profTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un profesor");
            idStudent = Integer.parseInt(idTextField.getText());
            grupa = Integer.parseInt(grupaTextField.getText());
            nume = numeTextField.getText();
            email = emailTextField.getText();
            prof = profTextField.getText();
            Student S = new Student(idStudent, grupa, nume, email, prof);
            srv.save(S);
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
        loadTableData();
    }

    @FXML
    public void handleUpdateButton(ActionEvent actionEvent) {
        int idStudent, grupa;
        String nume, email, prof;
        try {
            if (idTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un ID");
            if (numeTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un nume");
            if (grupaTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti o grupa");
            if (emailTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un email valid");
            if (profTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un profesor");
            idStudent = Integer.parseInt(idTextField.getText());
            grupa = Integer.parseInt(grupaTextField.getText());
            nume = numeTextField.getText();
            email = emailTextField.getText();
            prof = profTextField.getText();
            Student S = new Student(idStudent, grupa, nume, email, prof);
            srv.update(S);
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
        loadTableData();
    }

    @FXML
    public void handleDeleteButton(ActionEvent actionEvent) {
        try {
            if (idTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un ID");
            int idStudent = Integer.parseInt(idTextField.getText());
            srv.delete(idStudent);
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
        loadTableData();
    }

}
