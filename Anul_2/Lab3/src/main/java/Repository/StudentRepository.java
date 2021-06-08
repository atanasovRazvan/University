package Repository;
import Model.Student;
import Validator.Validator;


/**
 * class of repository for a student
 */
public class StudentRepository extends AbstractCRUDRepository<Integer,Student> {

    /**
     *
     * @param v - validator for a student
     */
    public StudentRepository(Validator<Student> v){
        super(v);
    }
}
