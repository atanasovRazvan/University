package Repository;
import Model.Student;
import Validator.*;
import org.junit.Test;


class AbstractCRUDRepositoryTest {
    private Validator<Student> v = new StudentValidator();
    private Repository<Integer,Student> repo = new StudentRepository(v);
    private Student s = new Student(1,226, "Pop Ion","pop.ion@gmail.com","prof");

    @Test
    void findOne() {
        try{
            repo.save(s);
            assert repo.findOne(1) == s;
        }
        catch (ValidationException e){
        }
    }

    @Test
    void findAll() {
        try{
            repo.save(s);
            Student s2 = new Student(2,221, "Pop Ion","pop.ion@gmail.com","prof");
            repo.save(s2);
        }
        catch (ValidationException e){
        }
    }

    @Test
    void save() {
        try{
            repo.save(s);
            assert repo.findOne(1) == s;
        }
        catch (ValidationException e){
        }
    }

    @Test
    void delete() {
        try{
            repo.save(s);
            Student s2 = new Student(2,221, "Pop Ion","pop.ion@gmail.com","prof");
            repo.save(s2);
            repo.delete(2);
            assert  repo.size() == 1;
        }
        catch (ValidationException e){
        }
    }

    @Test
    void update() {
        try{
            repo.save(s);
            Student s2 = new Student(1,221, "Pop Ion","pop.ion@gmail.com","prof");
            repo.save(s2);
            repo.update(s2);
            assert  repo.findOne(1).getGrupa()==221;
        }
        catch (ValidationException e){
        }
    }

    @Test
    void size() {
        try{
            repo.save(s);
            assert  repo.size() == 1;
        }
        catch (ValidationException e){
        }
    }
}