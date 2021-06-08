import controller.LoginWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repository.ClientRepository;
import repository.FlightRepository;
import repository.TicketRepository;
import service.Service;
import validators.ClientValidator;
import validators.FlightValidator;
import validators.TicketValidator;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        ClientValidator clientValidator = new ClientValidator();
        FlightValidator flightValidator = new FlightValidator();
        TicketValidator ticketValidator = new TicketValidator();

        ClientRepository clientRepository = new ClientRepository(clientValidator);
        FlightRepository flightRepository = new FlightRepository(flightValidator);
        TicketRepository ticketRepository = new TicketRepository(ticketValidator);

        Service service = new Service(clientRepository, flightRepository, ticketRepository);
        service.getAllDepartureLocations();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/loginwindow.fxml"));
        AnchorPane root = null;
        try {
            root = loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        LoginWindow controller = loader.getController();
        controller.setService(service);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
