package Repositories;

import Entities.Student;
import Validators.Validator;

/**
 * class of repository for a student
 */
public class StudentRepository extends AbstractCrudRepository<Integer, Student> {

    /**
     *
     * @param v - validator for a student
     */
    public StudentRepository(Validator<Student> v){
        super(v);
    }
}
