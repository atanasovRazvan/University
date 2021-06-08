package Service;

import Entities.Tema;
import Repositories.TemeRepository;

public class TemeService extends AbstractService<Integer, Tema> {

    public TemeService(TemeRepository repo){
        super(repo);
    }

}