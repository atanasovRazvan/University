package Controllers;

import Entities.Tema;
import Repositories.TemaXMLRepository;
import Service.TemaXMLService;
import Validators.ValidatorTema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TemaController {

    @FXML
    private TextArea myText;
    @FXML
    private javafx.scene.control.Button cancelButton;
    @FXML
    private TableView table;
    @FXML
    private TableColumn nrTemaCol, deadlineCol, startWeekCol, descriereCol;
    @FXML
    private TextField nrTemaTextField, deadlineTextField, startWeekTextField;
    @FXML
    private TextArea descriereTextField;
    private ValidatorTema v = new ValidatorTema();
    private TemaXMLRepository repo = new TemaXMLRepository(v);
    private TemaXMLService srv = new TemaXMLService(repo);

    @FXML
    public void loadTableData(){
        nrTemaCol.setCellValueFactory(new PropertyValueFactory("ID"));
        deadlineCol.setCellValueFactory(new PropertyValueFactory("deadline"));
        startWeekCol.setCellValueFactory(new PropertyValueFactory("saptPredare"));
        descriereCol.setCellValueFactory(new PropertyValueFactory("descriere"));
        List list = new ArrayList();
        srv.findAll().forEach(list::add);
        ObservableList data = FXCollections.observableList(list);
        table.setItems(data);
    }

    @FXML
    public void initialize(){
        myText.setEditable(false);
        loadTableData();
    }

    @FXML
    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleAddButton(ActionEvent actionEvent) throws Exception {
        int nrTema, deadline, startWeek;
        String descriere;
        if(nrTemaTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un Nr. Crt.");
        if(deadlineTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un deadline valid");
        if(descriereTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti o descriere valida");
        nrTema = Integer.parseInt(nrTemaTextField.getText());
        deadline = Integer.parseInt(deadlineTextField.getText());
        if(startWeekTextField.getText().equals("")) startWeek = 0;
        else startWeek = Integer.parseInt(startWeekTextField.getText());
        descriere = descriereTextField.getText();
        Tema T = new Tema(nrTema, startWeek, deadline, descriere);
        srv.save(T);
        loadTableData();
    }

    @FXML
    public void handleUpdateButton(ActionEvent actionEvent) throws Exception {
        int nrTema, deadline, startWeek;
        String descriere;
        if(nrTemaTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un Nr. Crt.");
        if(deadlineTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un deadline valid");
        if(descriereTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti o descriere valida");
        nrTema = Integer.parseInt(nrTemaTextField.getText());
        deadline = Integer.parseInt(deadlineTextField.getText());
        if(startWeekTextField.getText().equals("")) startWeek = 0;
        else startWeek = Integer.parseInt(startWeekTextField.getText());
        descriere = descriereTextField.getText();
        Tema T = new Tema(nrTema, startWeek, deadline, descriere);
        srv.update(T);
        loadTableData();
    }

    @FXML
    public void handleDeleteButton(ActionEvent actionEvent) throws Exception {
        int nrTema;
        if(nrTemaTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un Nr. Crt.");
        nrTema = Integer.parseInt(nrTemaTextField.getText());
        srv.delete(nrTema);
        loadTableData();
    }

}
