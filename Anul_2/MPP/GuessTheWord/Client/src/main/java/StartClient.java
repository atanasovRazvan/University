import Controllers.LoginCtrl;
import Services.IService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.rmi.Remote;


public class StartClient extends Application implements Remote {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {

            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:client-config.xml");
            IService server= (IService) factory.getBean("service");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmls/login.fxml"));
            AnchorPane root = loader.load();
            LoginCtrl controller = loader.getController();
            controller.setServer(server);
            Scene scene = new Scene(root);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Initialization error:"+e);
            e.printStackTrace();
        }

    }

}
