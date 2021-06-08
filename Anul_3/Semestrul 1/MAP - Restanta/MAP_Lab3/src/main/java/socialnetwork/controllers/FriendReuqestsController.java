package socialnetwork.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import socialnetwork.domain.FriendRequest;
import socialnetwork.domain.Utilizator;
import socialnetwork.service.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class FriendReuqestsController implements Observer{

    public TableView<FriendRequestDTO> table;
    public TableColumn<FriendRequestDTO, String> firstName;
    public TableColumn<FriendRequestDTO, String> lastName;
    public TableColumn<FriendRequestDTO, Button> acceptBtns;
    public TableColumn<FriendRequestDTO, Button> refuseBtns;
    public TableColumn<FriendRequestDTO, String> dateTime;
    public TableColumn<FriendRequestDTO, String> status;

    private Service service;
    private Utilizator user;

    public static class FriendRequestDTO extends FriendRequest{

        private Button accept, decline;
        private String firstName, lastName, datetime, frStatus;

        public Button getAccept() {
            return accept;
        }

        public void setAccept(Button accept) {
            this.accept = accept;
        }

        public Button getDecline() {
            return decline;
        }

        public void setDecline(Button decline) {
            this.decline = decline;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getFrStatus() {
            return frStatus;
        }

        public void setFrStatus(String frStatus) {
            this.frStatus = frStatus;
        }
    }

    public void initialize(){
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        acceptBtns.setCellValueFactory(new PropertyValueFactory<>("accept"));
        refuseBtns.setCellValueFactory(new PropertyValueFactory<>("decline"));
        dateTime.setCellValueFactory(new PropertyValueFactory<>("datetime"));
        status.setCellValueFactory(new PropertyValueFactory<>("frStatus"));

        Observable.getInstance().addObserver(this);
    }

    @Override
    public void loadData() {

        List<FriendRequestDTO> fr = service.getAllFriendRequests(user.getId()).stream()
                .map(req -> {
                    Button accBtn = new Button("Accept");
                    Button refBtn = new Button("Refuse");

                    if(req.getStatus().equals("ACCEPTED") || req.getStatus().equals("REFUSED")) {
                        accBtn.setDisable(true);
                        refBtn.setDisable(true);
                    }

                    accBtn.setOnAction(event -> {
                        service.handleFriendRequest(req.getId(), "A");
                        Observable.getInstance().notifyall();
                    });
                    refBtn.setOnAction(event -> {
                        service.handleFriendRequest(req.getId(), "R");
                        Observable.getInstance().notifyall();
                    });

                    FriendRequestDTO requestDTO = new FriendRequestDTO();
                    requestDTO.setFirstName(service.getUser(req.getSender()).getFirstName());
                    requestDTO.setLastName(service.getUser(req.getSender()).getLastName());
                    requestDTO.setAccept(accBtn);
                    requestDTO.setDecline(refBtn);
                    requestDTO.setDatetime(req.getLdt().toString());
                    requestDTO.setFrStatus(req.getStatus());
                    return requestDTO;
                }).collect(Collectors.toList());

        ObservableList<FriendRequestDTO> data = FXCollections.observableArrayList(fr);
        table.setItems(data);

    }

    public void setService(Service service, Utilizator user) {

        this.service = service;
        this.user = user;

        loadData();

    }
}
