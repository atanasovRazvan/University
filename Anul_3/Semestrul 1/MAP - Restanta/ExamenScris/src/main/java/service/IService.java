package service;

import model.Hotel;
import model.Reservation;
import model.SpecialOffer;

import java.util.Date;
import java.util.List;

public interface IService {

    List<Hotel> getAllHotelsFromLocation(String locationName);
    List<SpecialOffer> getSpecialOffersFromHotel(Double hotelId, Date startDate, Date endDate);
    String getClientName(Long clientId);
    List<SpecialOffer> showAllAvailableOffers(Long clientId);
    void makeReservation(Reservation reservation);

}
