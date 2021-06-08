package repository;

import model.Client;
import validators.Validator;

import java.time.LocalDateTime;
import java.util.List;

public class ClientRepository extends AbstractRepository<String, Client>{

    public ClientRepository(Validator<Client> validator) {
        super("data/client.txt", validator);
    }

    @Override
    public Client extractEntity(List<String> attributes) {
        return new Client(
                attributes.get(0),
                attributes.get(1)
        );
    }

    @Override
    protected String createEntityAsString(Client entity) {
        return entity.getId() + ';' + entity.getName();
    }
}
