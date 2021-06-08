package Service;

import java.util.stream.*;
import Entities.Student;
import Repositories.StudentXMLRepository;

public class StudentXMLService extends AbstractService<Integer, Student> {

    public StudentXMLService(StudentXMLRepository repo){
        super(repo);
    }

//    public Iterable<Student> filterByGroup(Integer grupa){
//        return this.getAll().stream()
//                .filter(x-> x.getGrupa().equals(grupa))
//                .collect(Collectors.toList());
//    }

}