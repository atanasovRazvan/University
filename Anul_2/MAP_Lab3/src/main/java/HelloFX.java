import Controllers.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ApplicationView.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        MainWindowController controller = loader.getController();

        stage.setTitle("Catalog Studenti");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}