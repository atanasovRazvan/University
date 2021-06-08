package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import service.Service;

public class MainWindowController {

    public TableColumn hotelCol;
    public TableView tableView;
    public TableColumn noRoomsCol;
    public TableColumn priceCol;
    public TableColumn typeCol;
    public ComboBox comboBox;

    private Long clientId;
    private Service service;

    public void setClient(Long clientId) {
        this.clientId = clientId;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void initialize(){

        String clientName = this.service.getClientName(this.clientId);
        Stage stage = (Stage) comboBox.getScene().getWindow();
        stage.setTitle(clientName);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Cluj",
                        "Bucuresti",
                        "Iasi"
                );
        comboBox.setItems(options);
    }
}
