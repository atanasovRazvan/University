package Repositories;

import Entities.Location;

import java.io.FileNotFoundException;

public class LocationRepository extends AbstractRepository<String, Location> {

    public LocationRepository() throws FileNotFoundException {
        super("locations.txt");
    }
}
