package socialnetwork;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import socialnetwork.config.ApplicationContext;
import socialnetwork.controllers.StartWindowController;
import socialnetwork.domain.*;
import socialnetwork.domain.validators.*;
import socialnetwork.repository.Repository;
import socialnetwork.repository.file.FriendRequestFile;
import socialnetwork.repository.file.FriendshipFile;
import socialnetwork.repository.file.MessageFile;
import socialnetwork.repository.file.UtilizatorFile;
import socialnetwork.repository.memory.InMemoryRepository;
import socialnetwork.service.Service;
import socialnetwork.ui.UserInterface;

public class Main extends Application{

    static class MyThread extends Thread{

        private UserInterface ui;

        public void setUI(UserInterface ui){
            this.ui = ui;
        }

        @Override
        public void run(){
            try {
                ui.run();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        try {

            Validator<Utilizator> userValidator = new UtilizatorValidator();
            Repository<Long, Utilizator> userRepository = new UtilizatorFile("data/users.csv", userValidator);

            Validator<Prietenie> friendshipValidator = new FriendshipValidator();
            Repository<Long, Prietenie> friendshipRepository = new FriendshipFile("data/friendships.csv", friendshipValidator);

            Validator<FriendRequest> friendRequestValidator = new FriendRequestValidator();
            Repository<Long, FriendRequest> friendRequestRepository = new FriendRequestFile("data/friendrequests.csv", friendRequestValidator);

            Validator<Message> messageValidator = new MessageValidator();
            Repository<Long, Message> messageRepository = new MessageFile("data/messages.csv", messageValidator);

            Service service = new Service(userRepository, friendshipRepository, friendRequestRepository, messageRepository);

            UserInterface ui = new UserInterface(service);
            MyThread uiThread = new MyThread();
            uiThread.setUI(ui);
            uiThread.start();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/start.fxml"));
            AnchorPane root = loader.load();
            StartWindowController controller = loader.getController();
            controller.setService(service);
            Scene scene = new Scene(root);
            primaryStage.setTitle("Messenger Launcher");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Initialization error:"+e);
            e.printStackTrace();
        }

    }
}


