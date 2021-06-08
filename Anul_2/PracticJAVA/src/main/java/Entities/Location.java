package Entities;


public class Location implements HasID<java.lang.String> {

    private String locationID, locationName, hotel;
    private Integer noRooms;
    private Double pricePerNight;

    public Location(String locationID, String locationName, String hotel, Integer noRooms, Double pricePerNight) {
        this.locationID = locationID;
        this.locationName = locationName;
        this.hotel = hotel;
        this.noRooms = noRooms;
        this.pricePerNight = pricePerNight;
    }

    public java.lang.String getLocationName() {
        return locationName;
    }

    public void setLocationName(java.lang.String locationName) {
        this.locationName = locationName;
    }

    public java.lang.String getHotel() {
        return hotel;
    }

    public void setHotel(java.lang.String hotel1) {
        hotel = hotel1;
    }

    public int getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(int noRooms) {
        this.noRooms = noRooms;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String getID() {
        return locationID;
    }

    @Override
    public void setID(String id) {
        locationID = id;
    }
}
