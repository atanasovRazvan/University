package Services;

import Entities.Client;
import Repositories.ClientRepository;

public class ClientService extends AbstractService<Long, Client> {

    public ClientService(ClientRepository repo){
        super(repo);
    }

}
