package repository;
import model.Client;
import model.Hotel;

import java.util.List;

public class HotelRepository extends AbstractRepository<Double, Hotel> {

    public List<Hotel> findAll() {
        return super.findAll("Hotel");
    }

}
