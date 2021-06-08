package model;

public class Item implements Entity {

    private String price, priceEstimation;
    private String description, id, ownerUsername, photoPath;
    private Integer version;
    private Double lat, lng;

    public Item(){}

    public Item(String price, String priceEstimation, String description, String ownerUsername, Double lat, Double lng, String photoPath) {
        this.price = price;
        this.priceEstimation = priceEstimation;
        this.description = description;
        this.ownerUsername = ownerUsername;
        this.lat = lat;
        this.lng = lng;
        this.photoPath = photoPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceEstimation() {
        return priceEstimation;
    }

    public void setPriceEstimation(String priceEstimation) {
        this.priceEstimation = priceEstimation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
