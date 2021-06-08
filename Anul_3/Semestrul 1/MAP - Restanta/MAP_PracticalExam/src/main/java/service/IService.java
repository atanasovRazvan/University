package service;

import model.Flight;

import java.time.LocalDate;
import java.util.List;

public interface IService {

    String getClientName(String username);
    List<String> getAllDepartureLocations();
    List<String> getAllLandingLocations();
    List<Flight> flightsByDayAndLocation(String from, String to, LocalDate date);
    void buyTicket(String username, Long flightId);
    Integer getFreeSeats(Long flightId);

}
