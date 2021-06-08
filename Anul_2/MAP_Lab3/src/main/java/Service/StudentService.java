package Service;

import Entities.Student;
import Repositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class StudentService extends AbstractService<Integer, Student> {

    public StudentService(StudentRepository repo){
        super(repo);
    }

//    public List<Student> filterByGroup(Integer grupa){
//        return this.getAll().stream()
//                .filter(x-> x.getGrupa().equals(grupa))
//                .collect(Collectors.toList());
//    }

}
