package Repository;
import Entities.*;
import Repositories.*;
import Validators.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AbstractCrudRepositoryTest {
    private Validator<Student> v = new ValidatorStudent();
    private Validator<Tema> vt = new ValidatorTema();
    private Validator <Nota> vn = new ValidatorNota();
    private CrudRepository<Integer,Student> repo = new StudentRepository(v);
    private CrudRepository<Integer, Tema> repot = new TemeRepository(vt);
    private CrudRepository<String, Nota> repon = new NoteRepository(vn);
    private Student s = new Student(1,226, "Pop Ion","pop.ion@gmail.com","prof");
    private Tema t = new Tema(1, 0,2,"Unittesting");
    private Nota n = new Nota(1, 1, "prof", 8, "Lipseste arhitectura stratificata!");

    /**
     * tests for the findOne method in repository
     */
    @Test
    public void findOne() {
        try{
            repo.save(s);
            repot.save(t);
            repon.save(n);
            assert repot.findOne(1)== t;
            assert repo.findOne(1) == s;
            assert repon.findOne("11") == n;
        }
        catch (ValidationException e){
        }
    }

    /**
     * tests for the findAll method in repository
     */
    @Test
    public void findAll() {
        try{
            repo.save(s);
            repot.save(t);
            repon.save(n);
            Student s2 = new Student(2,221, "Pop Ion","pop.ion@gmail.com","prof");
            Tema t2 = new Tema(2, 0,  6, "GUI");
            Nota n2 = new Nota(2, 2, "prof", 9, "Nu ai teste");
            repo.save(s2);
            repot.save(t2);
            repon.save(n2);
            int countS=0, countT=0, countN=0;
            for(Student ignored : repo.findAll()) countS++;
            for(Tema ignored : repot.findAll()) countT++;
            for(Nota ignored : repon.findAll()) countN++;
            assert countS==2;
            assert countT==2;
            assert countN==2;

        }
        catch (ValidationException e){
        }
    }

    /**
     * Tests for the save method in repository
     */
    @Test
    public void save() {
        try{
            repo.save(s);
            repot.save(t);
            repon.save(n);
            assert repot.findOne(1) == t;
            assert repo.findOne(1) == s;
            assert repon.findOne("11") == n;
        }
        catch (ValidationException e){
            assert true;
        }
    }

    /**
     * Tests for the delete method in repository
     */
    @Test
    public void delete() {
        try{
            repo.save(s);
            repot.save(t);
            repon.save(n);
            Tema t2 = new Tema(2, 0,  6, "GUI");
            Student s2 = new Student(2,221, "Pop Ion","pop.ion@gmail.com","prof");
            Nota n2 = new Nota(2,2,"prof", 3, "cv");
            repo.save(s2);
            repot.save(t2);
            repon.save(n2);
            repot.delete(2);
            repo.delete(2);
            repon.delete("22");
            assert  repo.size() == 1;
            assert  repot.size() == 1;
            assert  repon.size() == 1;
        }
        catch (ValidationException e){
        }
    }

    /**
     * Tests for the update method in repository
     */
    @Test
    public void update() {
        try{
            repo.save(s);
            repot.save(t);
            repon.save(n);
            Tema t2 = new Tema(1, 0,  6, "GUI");
            Student s2 = new Student(1,221, "Pop Ion","pop.ion@gmail.com","prof");
            Nota n2 = new Nota(1, 1, "prof", 10, "Irelevant");
            repo.save(s2);
            repot.save(t2);
            repon.save(n2);
            repo.update(s2);
            repot.update(t2);
            repon.update(n2);
            assert  repo.findOne(1).getGrupa()==221;
            assert  repot.findOne(1).getDeadline()==8;
            assert  repon.findOne("11").getFeedback()=="Irelevant";

        }
        catch (ValidationException e){
        }
    }

    /**
     * Tests for the size method in repository
     */
    @Test
    public void size() {
        try{
            repo.save(s);
            repot.save(t);
            repon.save(n);
            assert  repot.size()==1;
            assert  repo.size() == 1;
            assert  repon.size() == 1;
        }
        catch (ValidationException e){
        }
    }
}