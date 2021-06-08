package Service;

import Entities.Tema;
import Repositories.TemaXMLRepository;
import Repositories.TemeRepository;

public class TemaXMLService extends AbstractService<Integer, Tema> {

    public TemaXMLService(TemaXMLRepository repo){
        super(repo);
    }

}