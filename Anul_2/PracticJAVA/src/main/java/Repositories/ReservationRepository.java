package Repositories;

import Entities.Reservation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReservationRepository extends AbstractRepository<Long, Reservation> {

    public ReservationRepository() throws FileNotFoundException {
        super("reservations.txt");
    }

    @Override
    public void loadData() throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader("clients.txt"));
        String st;
        while((st = buffer.readLine()) != null){
            String[] arr = st.split(",", 2);
            this.save(new Reservation(Long.parseLong(arr[0]), arr[1]));
        }
    }

    @Override
    public void writeToFile() throws FileNotFoundException {

    }
}
