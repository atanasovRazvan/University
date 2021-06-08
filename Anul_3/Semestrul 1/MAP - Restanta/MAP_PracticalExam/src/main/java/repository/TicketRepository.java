package repository;

import model.Ticket;
import validators.Validator;

import java.time.LocalDateTime;
import java.util.List;

public class TicketRepository extends AbstractRepository<String, Ticket>{

    public TicketRepository(Validator<Ticket> validator) {
        super("data/ticket.txt", validator);
    }

    @Override
    public Ticket extractEntity(List<String> attributes) {

        return new Ticket(
                attributes.get(0),
                Long.parseLong(attributes.get(1)),
                LocalDateTime.parse(attributes.get(2)));
    }

    @Override
    protected String createEntityAsString(Ticket entity) {
        return entity.getUsername() + ';' + entity.getFlightId() + ';' + entity.getPurchaseTime();
    }
}
