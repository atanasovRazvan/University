import controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static List<Long> clients = new ArrayList<>();

    public static void main(String[] args){

        for(String client: args){
            clients.add(Long.parseLong(client.substring(7, client.length()-1)));
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Service service = new Service();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/mainwindow.fxml"));
        AnchorPane root = null;
        try {
            root = loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        AnchorPane finalRoot = root;
        clients.forEach(clientId -> {
            System.out.println(clientId);
            MainWindowController controller = loader.getController();
            controller.setClient(clientId);
            controller.setService(service);
            Scene scene = new Scene(finalRoot);
            Stage newStage = new Stage();
            newStage.setTitle("");
            newStage.setScene(scene);
            newStage.show();
        });

    }
}