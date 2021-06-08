package model;

public class Location implements Entity<Double> {

    private Double id;
    private String locationName;

    public Location(String locationName) {
        this.locationName = locationName;
    }

    public Location(){}

    @Override
    public Double getId() {
        return id;
    }

    @Override
    public void setId(Double id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
