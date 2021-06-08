package model;

import java.util.Date;

public class SpecialOffer implements Entity<Double> {

    private Double id;
    private Double hotelId;
    private Date startDate;
    private Date endDate;
    private Integer percents;

    public SpecialOffer(Double hotelId, Date startDate, Date endDate, Integer percents) {
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percents = percents;
    }

    public SpecialOffer() {
    }

    @Override
    public Double getId() {
        return id;
    }

    @Override
    public void setId(Double id) {
        this.id = id;
    }

    public Double getHotelId() {
        return hotelId;
    }

    public void setHotelId(Double hotelId) {
        this.hotelId = hotelId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPercents() {
        return percents;
    }

    public void setPercents(Integer percents) {
        this.percents = percents;
    }
}
