package scs.ubb.map.services.service;

import scs.ubb.map.domain.Student;
import scs.ubb.map.repository.CrudRepository;

public class StudentService extends Service<Long, Student> {
    public StudentService(CrudRepository<Long, Student> repository) {
        super(repository);
    }
}
