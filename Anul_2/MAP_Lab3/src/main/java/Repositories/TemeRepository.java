package Repositories;

import Entities.Tema;
import Validators.Validator;

/**
 * class of repository for a homework
 */
public class TemeRepository extends AbstractCrudRepository<Integer, Tema> {

    /**
     *
     * @param v - validator for a homework
     */
    public TemeRepository(Validator<Tema> v){
        super(v);
    }
}
