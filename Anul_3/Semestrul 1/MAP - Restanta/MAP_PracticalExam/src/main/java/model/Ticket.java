package model;

import java.time.LocalDateTime;

public class Ticket implements Entity<String> {

    private String username;
    private Long flightId;
    private LocalDateTime purchaseTime;

    public Ticket(String username, Long flightId, LocalDateTime purchaseTime) {
        this.username = username;
        this.flightId = flightId;
        this.purchaseTime = purchaseTime;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    @Override
    public String getId() {
        return this.username + "_" + this.flightId + "_" + this.getPurchaseTime();
    }

    @Override
    public void setId(String s) {
        this.username = s.split("_")[0];
        this.flightId = Long.parseLong(s.split("_")[1]);
        this.purchaseTime = LocalDateTime.parse(s.split("_")[2]);
    }
}
