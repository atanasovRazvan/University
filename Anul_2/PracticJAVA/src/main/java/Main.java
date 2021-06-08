import Repositories.ClientRepository;
import Repositories.LocationRepository;
import Repositories.ReservationRepository;
import Services.ClientService;
import Services.LocationService;
import Services.ReservationService;
import UI.UserInterface;

public class Main {
    public static void main(String[] args) {

        ClientRepository clientRepo = new ClientRepository();
        LocationRepository locationRepo = new LocationRepository();
        ReservationRepository reservationRepo = new ReservationRepository();

        ClientService clientSrv = new ClientService(clientRepo);
        LocationService locationSrv = new LocationService(locationRepo);
        ReservationService reservationSrv = new ReservationService(reservationRepo);

        UserInterface UI = new UserInterface(clientSrv, locationSrv, reservationSrv, args);
        UI.run();

    }
}
