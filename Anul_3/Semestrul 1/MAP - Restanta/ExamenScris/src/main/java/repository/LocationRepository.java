package repository;

import model.Client;
import model.Location;

import java.util.List;

public class LocationRepository extends AbstractRepository<Double, Location> {

    public List<Location> findAll() {
        return super.findAll("Location");
    }

}
