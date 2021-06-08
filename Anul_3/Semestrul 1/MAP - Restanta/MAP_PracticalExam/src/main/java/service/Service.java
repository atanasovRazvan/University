package service;

import model.Flight;
import model.Ticket;
import repository.ClientRepository;
import repository.FlightRepository;
import repository.RepositoryException;
import repository.TicketRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Service implements IService {

    private ClientRepository clientRepository;
    private FlightRepository flightRepository;
    private TicketRepository ticketRepository;

    public Service(ClientRepository clientRepository, FlightRepository flightRepository, TicketRepository ticketRepository){
        this.clientRepository = clientRepository;
        this.flightRepository = flightRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public String getClientName(String username) {

        return this.clientRepository.findOne(username).getName();

    }

    @Override
    public List<String> getAllDepartureLocations() {

        return this.flightRepository.findAll().stream()
                .map(Flight::getFrom)
                .distinct()
                .collect(Collectors.toList());

    }

    @Override
    public List<String> getAllLandingLocations() {
        return this.flightRepository.findAll().stream()
                .map(Flight::getTo)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> flightsByDayAndLocation(String from, String to, LocalDate date) {

        return this.flightRepository.findAll().stream()
                .filter(flight -> {
                    Integer day = date.getDayOfMonth();
                    Integer month = date.getMonthValue();
                    if((flight.getFrom().equals(from) || from.isEmpty()) && (flight.getTo().equals(to) || to.isEmpty()) &&
                            (flight.getDepartureTime().getDayOfMonth() == day && flight.getDepartureTime().getMonthValue() == month))
                        return true;
                    return false;
                })
                .collect(Collectors.toList());

    }

    @Override
    public void buyTicket(String username, Long flightId) {
        if(this.getFreeSeats(flightId).equals(0))
            throw new RepositoryException("Tickets already sold!");

        Ticket ticket = new Ticket(username, flightId, LocalDateTime.now());
        ticketRepository.save(ticket);
    }

    @Override
    public Integer getFreeSeats(Long flightId) {

        AtomicReference<Integer> freeSeats = new AtomicReference<>(0);
        ticketRepository.findAll().forEach(ticket -> {
            if(ticket.getFlightId().equals(flightId))
                freeSeats.getAndSet(freeSeats.get() + 1);
        });
        return flightRepository.findOne(flightId).getSeats() - freeSeats.get();

    }


}
