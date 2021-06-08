package scs.ubb.map.services.service;

import scs.ubb.map.domain.Homework;
import scs.ubb.map.repository.CrudRepository;

public class HomeworkService extends Service<Integer, Homework> {
    public HomeworkService(CrudRepository<Integer, Homework> repository) {
        super(repository);
    }
}
