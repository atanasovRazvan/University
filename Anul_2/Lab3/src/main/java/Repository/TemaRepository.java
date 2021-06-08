package Repository;
import Model.Tema;
import Validator.Validator;


/**
 * class of repository for a homework
 */
public class TemaRepository extends AbstractCRUDRepository<Integer, Tema> {

    /**
     *
     * @param v - validator for a homework
     */
    public TemaRepository(Validator<Tema> v){
        super(v);
    }
}
