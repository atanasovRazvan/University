package Services;

import Entities.Location;
import Repositories.LocationRepository;

import java.util.List;
import java.util.stream.Collectors;

public class LocationService extends AbstractService<String, Location>{

    public LocationService(LocationRepository repo){
        super(repo);
    }

    public List<Location> filterLocation(String locationName){
        return this.findAll().stream()
                .filter(x-> x.getLocationName().equals(locationName))
                .collect(Collectors.toList());
    }

}
