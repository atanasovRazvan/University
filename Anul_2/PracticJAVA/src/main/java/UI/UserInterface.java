package UI;

import Services.ClientService;
import Services.LocationService;
import Services.ReservationService;

import java.util.Scanner;

public class UserInterface {

    private ClientService clientSrv;
    private LocationService locationSrv;
    private ReservationService reservationSrv;
    private String[] args;

    public UserInterface(ClientService s1, LocationService s2, ReservationService s3, String[] argss){
        clientSrv = s1;
        locationSrv = s2;
        reservationSrv = s3;
        args = argss;
    }

    private void do1(){

        System.out.println(locationSrv.filterLocation(args[0]));

    }

    private void do2(){

        System.out.println(reservationSrv.nrRezervariAnCurent());

    }

    private void do3(){

    }

    private void do4(){

    }

    public void run(){

        Scanner scanner = new Scanner(System.in);

        while(true){

            System.out.println("Selectati cerinta (1, 2, 3 sau 4): ");
            String cmd = scanner.nextLine();
            switch(cmd){
                case "1": do1(); break;
                case "2": do2(); break;
                case "3": do3(); break;
                case "4": do4(); break;
            }

            System.out.println("Doriti sa continuati? (y/n) ");
            cmd = scanner.nextLine();
            if(cmd.equals("n")) break;

        }

    }

}
