package model;

import java.time.LocalDateTime;

public class FlightDTO {

    private Long flightId;
    private String from;
    private String to;
    private LocalDateTime departureTime;
    private LocalDateTime landingTime;
    private Integer seats;
    private Integer boughtSeats;

    public FlightDTO(Long flightId, String from, String to, LocalDateTime departureTime, LocalDateTime landingTime, Integer seats, Integer boughtSeats) {
        this.flightId = flightId;
        this.from = from;
        this.to = to;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
        this.seats = seats;
        this.boughtSeats = boughtSeats;
    }

    public FlightDTO(){}

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(LocalDateTime landingTime) {
        this.landingTime = landingTime;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getBoughtSeats() {
        return boughtSeats;
    }

    public void setBoughtSeats(Integer boughtSeats) {
        this.boughtSeats = boughtSeats;
    }
}
