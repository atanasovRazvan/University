package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Flight;
import model.FlightDTO;
import repository.RepositoryException;
import service.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainWindowController implements Observer {

    public Button buyButton;
    public Label pageLabel;
    public Button nextBtn;
    public Button previousBtn;
    public DatePicker dateInput;
    public TableColumn<FlightDTO, Integer> boughtSeatsCol;
    public TableColumn<FlightDTO, Integer> noSeatsCol;
    public TableColumn<FlightDTO, LocalDateTime> landingTimeCol;
    public TableColumn<FlightDTO, LocalDateTime> departureTimeCol;
    public TableColumn<FlightDTO, String> toCol;
    public TableColumn<FlightDTO, String> fromCol;
    public TableColumn<FlightDTO, Long> idCol;
    public TableView<FlightDTO> tableView;
    public Button searchBtn;
    public ComboBox fromInput;
    public ComboBox toInput;

    private Integer pageNumber;

    private Service service;
    private String username;
    private Long flightId;

    private List<FlightDTO> currentFlights;

    public void setNeeds(Service service, String text) {
        this.service = service;
        this.username = text;
        currentFlights = new ArrayList<>();
        setData();
    }

    private void setData(){
        fromInput.setItems(FXCollections.observableArrayList(this.service.getAllDepartureLocations()));
        toInput.setItems(FXCollections.observableArrayList(this.service.getAllLandingLocations()));
    }

    public void initialize(){

        Observable.getInstance().addObserver(this);

        tableView.setOnMouseClicked(event -> {

            if(tableView.getSelectionModel().getSelectedItems().size() < 2) {
                FlightDTO flightDTO = tableView.getSelectionModel().getSelectedItem();
                this.flightId = flightDTO.getFlightId();
            }
        });

        boughtSeatsCol.setCellValueFactory(new PropertyValueFactory<>("boughtSeats"));
        noSeatsCol.setCellValueFactory(new PropertyValueFactory<>("seats"));
        landingTimeCol.setCellValueFactory(new PropertyValueFactory<>("landingTime"));
        departureTimeCol.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        toCol.setCellValueFactory(new PropertyValueFactory<>("to"));
        fromCol.setCellValueFactory(new PropertyValueFactory<>("from"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("flightId"));

    }

    private void refreshTable(){
        pageLabel.setText("page " + pageNumber + " of " + (this.currentFlights.size()/5 + 1));
        List<FlightDTO> visibleFlights = new ArrayList<>();
        for(int i = 5*this.pageNumber - 5; i < 5*this.pageNumber && i < currentFlights.size(); i++)
            visibleFlights.add(currentFlights.get(i));

        ObservableList<FlightDTO> data = FXCollections.observableArrayList(visibleFlights);
        tableView.setItems(data);
    }

    public void handlePrevious(ActionEvent actionEvent) {
        this.pageNumber --;
        if(this.pageNumber == 1)
            previousBtn.setDisable(true);
        nextBtn.setDisable(false);
        refreshTable();
    }

    public void handleNext(ActionEvent actionEvent) {
        this.pageNumber ++;
        previousBtn.setDisable(false);
        if(5 * this.pageNumber >= currentFlights.size())
            nextBtn.setDisable(true);
        refreshTable();
    }

    public void handleBuy(ActionEvent actionEvent) {

        try {
            this.service.buyTicket(this.username, this.flightId);
            Observable.notifyall();
        }
        catch (RepositoryException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("There are no more available seats for this flight!");
            alert.show();
        }

    }

    public void handleSearch(ActionEvent actionEvent) {

        loadData();
        this.pageNumber = 1;
        refreshTable();

    }

    public void loadData() {

        currentFlights.clear();
        LocalDate date = dateInput.getValue();
        String from = fromInput.getValue().toString();
        String to = toInput.getValue().toString();

        try{
            List<Flight> flights = this.service.flightsByDayAndLocation(from, to, date);
            List<FlightDTO> dtoFlights = flights.stream().map(flight -> {
                FlightDTO flightDTO = new FlightDTO(flight.getId(), flight.getFrom(), flight.getTo(), flight.getDepartureTime(),
                        flight.getLandingTime(), flight.getSeats(), this.service.getFreeSeats(flight.getId()));
                return flightDTO;
            })
                    .collect(Collectors.toList());

            currentFlights.addAll(dtoFlights);
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No results found");
            alert.show();
        }

    }

    @Override
    public void refreshData() {
        loadData();
        refreshTable();
    }
}
