package model;

import java.time.LocalDateTime;

public class Reservation implements Entity<Double>{

    private Double id;
    private Long clientId;
    private Double hotelId;
    private LocalDateTime startDate;
    private Integer noNights;

    public Reservation(Long clientId, Double hotelId, LocalDateTime startDate, Integer noNights) {
        this.clientId = clientId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.noNights = noNights;
    }

    public Reservation() {
    }

    @Override
    public Double getId() {
        return id;
    }

    @Override
    public void setId(Double id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Double getHotelId() {
        return hotelId;
    }

    public void setHotelId(Double hotelId) {
        this.hotelId = hotelId;
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
}
