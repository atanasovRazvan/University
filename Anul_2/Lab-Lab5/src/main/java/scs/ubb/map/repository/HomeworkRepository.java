package scs.ubb.map.repository;

import scs.ubb.map.domain.Homework;
import scs.ubb.map.validators.Validator;

public class HomeworkRepository extends InMemoryRepository<Integer, Homework> {
    public HomeworkRepository(Validator<Homework> validator) {
        super(validator);
    }
}
