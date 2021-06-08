package repository;

import model.Client;

import java.util.List;

public class ClientRepository extends AbstractRepository<Long, Client> {

    public List<Client> findAll() {
        return super.findAll("Client");
    }

}
