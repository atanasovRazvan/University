package Entities;

import java.time.LocalDateTime;

public class Reservation implements HasID<Long> {

    private Long id;
    private Client client;
    private Location location;
    private LocalDateTime startDate;
    private Integer noNights;

    public Reservation(Long id, Client client, Location location, LocalDateTime startDate, Integer noNights) {
        this.id = id;
        this.client = client;
        this.location = location;
        this.startDate = startDate;
        this.noNights = noNights;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getNoNights() {
        return noNights;
    }

    public void setNoNights(Integer noNights) {
        this.noNights = noNights;
    }

    @Override
    public Long getID() {
        return id;
    }

    @Override
    public void setID(Long aLong) {
        id = aLong;
    }
}
