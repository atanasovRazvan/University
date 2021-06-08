package socialnetwork.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import socialnetwork.domain.Friend;
import socialnetwork.domain.Message;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.service.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainWindowController implements Observer {

    @FXML
    public TableView<Friend> friends;
    @FXML
    public TableColumn<Friend, String> firstNameCol;
    @FXML
    public TableColumn<Friend, String> lastNameCol;
    @FXML
    public TableColumn<Friend, LocalDateTime> dateTimeCol;
    public ChoiceBox<String> settings;
    public Button friendRequestsBtn;
    public Button addBtn;
    public TextArea conversation;
    public Button sendMessageBtn;
    public TextArea messageHolder;

    private Service service;
    private Utilizator user;
    private Utilizator actualFriend;
    private Friend friend;
    private List<Friend> selectedfriends;
    private Utilizator friendRightClicked;

    public void setService(Service service, Utilizator user) {
        this.service = service;
        this.user = user;
        loadData();
    }

    public void initialize(){

        Observable.getInstance().addObserver(this);
        selectedfriends = new ArrayList<>();

        friends.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ContextMenu cm = new ContextMenu();
        MenuItem mi = new MenuItem("Delete friend");
        mi.setOnAction(t -> {
            this.service.removeFriendship(user, friendRightClicked);
            Observable.notifyall();
        });

        cm.getItems().add(mi);

        friends.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
            if(t.getButton() == MouseButton.SECONDARY) {
                cm.show(friends, t.getScreenX(), t.getScreenY());
                Friend toDelete = friends.getSelectionModel().getSelectedItem();
                friendRightClicked = this.service.getUserByName(toDelete.getFirstName(), toDelete.getLastName());
            }
        });

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dateTimeCol.setCellValueFactory(new PropertyValueFactory<>("dateTime"));

        friends.setOnMouseClicked(event -> {

            if(friends.getSelectionModel().getSelectedItems().size() < 2) {
                friend = friends.getSelectionModel().getSelectedItem();
                loadConversation(friend);
            }
            else {
                System.out.println("mai mult de 2");
                selectedfriends.clear();
                selectedfriends.addAll(friends.getSelectionModel().getSelectedItems());
            }
        });

        List<String> settingList = new ArrayList<>();
        settingList.add("delete account");
        settingList.add("add friend");
        ObservableList<String> observableSettingList = FXCollections.observableList(settingList);
        settings.setItems(observableSettingList);
    }

    public void loadData() {

        List<Friend> friendList = service.getFriends(user.getId());

        ObservableList<Friend> data = FXCollections.observableArrayList(friendList);
        friends.setItems(data);

        if(friend!=null)
            loadConversation(friend);

    }

    public void openRequests(ActionEvent actionEvent) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/friendRequests.fxml"));
            AnchorPane root = loader.load();
            FriendReuqestsController controller = loader.getController();
            controller.setService(service, user);
            Scene scene = new Scene(root);
            Stage newWindow = new Stage();
            newWindow.setTitle("Friend requests");
            newWindow.setScene(scene);
            newWindow.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void openAddFriend(ActionEvent actionEvent) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/addFriend.fxml"));
            AnchorPane root = loader.load();
            AddFriendController controller = loader.getController();
            controller.setService(service, user);
            Scene scene = new Scene(root);
            Stage newWindow = new Stage();
            newWindow.setTitle("Add friend");
            newWindow.setScene(scene);
            newWindow.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void loadConversation(Friend friend){

        actualFriend = null;
        conversation.clear();

        for(Utilizator user : service.getAllUsers()){
            if(user.getFirstName().equals(friend.getFirstName()) && user.getLastName().equals(friend.getLastName()))
                actualFriend = user;
        }

        assert actualFriend != null;
        service.showConversation(user.getId(), actualFriend.getId()).forEach(pair -> {
            conversation.appendText(pair.getValue());
            conversation.appendText("\n");
        });
    }

    public void sendMessage(ActionEvent actionEvent) {
        if(selectedfriends.size() > 0){
            selectedfriends.forEach(friend1 -> {
                Utilizator toSend = null;
                for (Utilizator user : service.getAllUsers()) {
                    if (user.getFirstName().equals(friend1.getFirstName()) && user.getLastName().equals(friend1.getLastName()))
                        toSend = user;
                }
                Message message = new Message(messageHolder.getText(), this.user.getId(), toSend.getId(), LocalDateTime.now());
                service.saveMessage(message);
            });
        }
        else {
            Message message = new Message(messageHolder.getText(), this.user.getId(), this.actualFriend.getId(), LocalDateTime.now());
            service.saveMessage(message);
        }
        Observable.getInstance().notifyall();
    }
}
