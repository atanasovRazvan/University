package scs.ubb.map.repository;

import scs.ubb.map.domain.Student;
import scs.ubb.map.validators.Validator;

public class StudentRepository extends InMemoryRepository<Long, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}
