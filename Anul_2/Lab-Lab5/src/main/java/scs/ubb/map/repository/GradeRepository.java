package scs.ubb.map.repository;

import scs.ubb.map.domain.Grade;
import scs.ubb.map.validators.Validator;

public class GradeRepository extends InMemoryRepository<String, Grade> {
    public GradeRepository(Validator<Grade> validator) {
        super(validator);
    }
}
