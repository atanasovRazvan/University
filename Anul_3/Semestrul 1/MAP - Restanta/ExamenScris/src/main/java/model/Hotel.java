package model;

public class Hotel implements Entity<Double> {

    private Double id;
    private Double locationId;
    private String hotelName;
    private Integer noRooms;
    private Double pricePerNight;
    private TypeEnum type;

    public Hotel(Double locationId, String hotelName, Integer noRooms, Double pricePerNight, TypeEnum type) {
        this.locationId = locationId;
        this.hotelName = hotelName;
        this.noRooms = noRooms;
        this.pricePerNight = pricePerNight;
        this.type = type;
    }

    public Hotel(){}

    @Override
    public Double getId() {
        return id;
    }

    @Override
    public void setId(Double id) {
        this.id = id;
    }

    public Double getLocationId() {
        return locationId;
    }

    public void setLocationId(Double locationId) {
        this.locationId = locationId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getNoRooms() {
        return noRooms;
    }

    public void setNoRooms(Integer noRooms) {
        this.noRooms = noRooms;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }
}
