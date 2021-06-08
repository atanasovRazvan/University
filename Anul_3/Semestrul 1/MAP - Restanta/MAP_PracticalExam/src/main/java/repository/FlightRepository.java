package repository;

import model.Flight;
import validators.Validator;

import java.time.LocalDateTime;
import java.util.List;

public class FlightRepository extends AbstractRepository<Long, Flight> {
    public FlightRepository(Validator<Flight> validator) {
        super("data/flight.txt", validator);
    }

    @Override
    public Flight extractEntity(List<String> attributes) {
        return new Flight(
                Long.parseLong(attributes.get(0)),
                attributes.get(1),
                attributes.get(2),
                LocalDateTime.parse(attributes.get(3)),
                LocalDateTime.parse(attributes.get(4)),
                Integer.parseInt(attributes.get(5))
        );
    }

    @Override
    protected String createEntityAsString(Flight entity) {
        return entity.getId() + ';' + entity.getFrom() + ';' + entity.getTo() + ';' + entity.getDepartureTime() +
                ';' + entity.getLandingTime() + ';' + entity.getSeats();
    }
}
