package Repositories;

import Entities.Nota;
import Validators.*;

public class NoteRepository extends AbstractCrudRepository<String, Nota> {


    /**
     * @param v - Validator for a grade
     */
    public NoteRepository(Validator<Nota> v){
        super(v);
    }

}
