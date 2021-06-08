package socialnetwork.controllers;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import socialnetwork.domain.FriendRequest;
import socialnetwork.domain.Utilizator;
import socialnetwork.service.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddFriendController implements Observer {

    public TableView<FriendRequestCreator> table;
    public TableColumn<FriendRequestCreator, String> firstNameCol;
    public TableColumn<FriendRequestCreator, String> lastNameCol;
    public TableColumn<FriendRequestCreator, Button> sendBtn;
    public TableColumn<FriendRequestCreator, Button> cancelBtn;
    public TableColumn<FriendRequestCreator, String> statusCol;
    public TextField searchingInput;

    private Service service;
    private Utilizator user;

    public static class FriendRequestCreator{

        private String firstName, lastName, status;
        private Button send, cancel;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Button getSend() {
            return send;
        }

        public void setSend(Button send) {
            this.send = send;
        }

        public Button getCancel() {
            return cancel;
        }

        public void setCancel(Button cancel) {
            this.cancel = cancel;
        }
    }


    public void setService(Service service, Utilizator user) {

        this.service = service;
        this.user = user;
        loadData();

    }

    public void initialize(){

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        sendBtn.setCellValueFactory(new PropertyValueFactory<>("send"));
        cancelBtn.setCellValueFactory(new PropertyValueFactory<>("cancel"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        searchingInput.textProperty().addListener(event -> loadData());

        Observable.getInstance().addObserver(this);

    }

    @Override
    public void loadData() {

        if (!searchingInput.getText().isEmpty()) {
            List<Utilizator> strangers = service.getStrangers(this.user.getId(), searchingInput.getText());

            List<FriendRequestCreator> list = new ArrayList<>();

            strangers.forEach(stranger -> {
                FriendRequestCreator frc = new FriendRequestCreator();
                frc.setFirstName(stranger.getFirstName());
                frc.setLastName(stranger.getLastName());

                Button sendBTN = new Button("Send");
                Button cancelBTN = new Button("Cancel");

                String status = service.getFriendRequestStatus(this.user.getId(), stranger.getId());

                if (status.equals("PENDING")) frc.setStatus("Pending");
                if (status.equals("NONE")) frc.setStatus("-");
                if (status.equals("ACCEPTED") || status.equals("REFUSED")) frc.setStatus("Solutioned");

                if (!status.equals("NONE")) {
                    if (status.equals("PENDING")) {
                        sendBTN.setDisable(true);
                    } else {
                        sendBTN.setDisable(true);
                        cancelBTN.setDisable(true);
                    }
                } else {
                    cancelBTN.setDisable(true);
                }

                FriendRequest friendRequest = new FriendRequest(this.user.getId(), stranger.getId());

                sendBTN.setOnAction(event -> {
                    friendRequest.setLdt(LocalDateTime.now());
                    service.addFriendRequest(friendRequest);
                    Observable.getInstance().notifyall();
                });

                cancelBTN.setOnAction(event -> {
                    service.deleteFriendRequest(friendRequest);
                    Observable.getInstance().notifyall();
                });

                frc.setSend(sendBTN);
                frc.setCancel(cancelBTN);

                list.add(frc);

            });

            table.setItems(FXCollections.observableArrayList(list));

        }
    }

}
