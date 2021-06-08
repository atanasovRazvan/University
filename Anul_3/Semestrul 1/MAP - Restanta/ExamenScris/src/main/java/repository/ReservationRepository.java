package repository;

import model.Location;
import model.Reservation;

import java.util.List;

public class ReservationRepository extends AbstractRepository<Double, Reservation> {

    public List<Reservation> findAll() {
        return super.findAll("Reservation");
    }

    @Override
    public void add(Reservation reservation) {
        super.add(reservation);
    }
}
