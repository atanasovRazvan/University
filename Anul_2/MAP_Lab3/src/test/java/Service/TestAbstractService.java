package Service;
import Entities.*;
import Repositories.*;
import Validators.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestAbstractService {
    private Validator<Student> v = new ValidatorStudent();
    private Validator<Tema> vt = new ValidatorTema();
    private Validator <Nota> vn = new ValidatorNota();
    private StudentRepository repo = new StudentRepository(v);
    private TemeRepository repot = new TemeRepository(vt);
    private NoteRepository repon = new NoteRepository(vn);
    private AbstractService<Integer, Student> stSrv = new StudentService(repo);
    private AbstractService<Integer, Tema> temaSrv = new TemeService(repot);
    private AbstractService<String, Nota> notaSrv = new NoteService(repon);
    private Student s = new Student(1,226, "Pop Ion","pop.ion@gmail.com","prof");
    private Tema t = new Tema(1, 0,2,"Unittesting");
    private Nota n = new Nota(1, 1, "prof", 8, "Lipseste arhitectura stratificata!");

    /**
     * tests for the findOne method in service
     */
    @Test
    public void findOne() {
        try{
            stSrv.save(s);
            temaSrv.save(t);
            notaSrv.save(n);
            assert stSrv.findOne(1) == s;
            assert temaSrv.findOne(1) == t;
            assert notaSrv.findOne("11") == n;
        }
        catch (ValidationException e){
        }
    }

    /**
     * tests for the findAll method in service
     */
    @Test
    public void findAll() {
        try{
            stSrv.save(s);
            temaSrv.save(t);
            notaSrv.save(n);
            Student s2 = new Student(2,221, "Pop Ion","pop.ion@gmail.com","prof");
            Tema t2 = new Tema(2, 0,  6, "GUI");
            Nota n2 = new Nota(2, 2, "prof", 9, "Nu ai teste");
            stSrv.save(s2);
            temaSrv.save(t2);
            notaSrv.save(n2);
            int countS=0, countT=0, countN=0;
            for(Student ignored : stSrv.findAll()) countS++;
            for(Tema ignored : temaSrv.findAll()) countT++;
            for(Nota ignored : notaSrv.findAll()) countN++;
            assert countS==2;
            assert countT==2;
            assert countN==2;

        }
        catch (ValidationException e){
        }
    }

    /**
     * Tests for the save method in service
     */
    @Test
    public void save() {
        try{
            stSrv.save(s);
            temaSrv.save(t);
            notaSrv.save(n);
            assert stSrv.findOne(1) == s;
            assert temaSrv.findOne(1) == t;
            assert notaSrv.findOne("11") == n;
        }
        catch (ValidationException e){
            assert true;
        }
    }

    /**
     * Tests for the delete method in service
     */
    @Test
    public void delete() {
        try{
            stSrv.save(s);
            temaSrv.save(t);
            notaSrv.save(n);
            Tema t2 = new Tema(2, 0,  6, "GUI");
            Student s2 = new Student(2,221, "Pop Ion","pop.ion@gmail.com","prof");
            Nota n2 = new Nota(2,2,"prof", 3, "cv");
            stSrv.save(s2);
            temaSrv.save(t2);
            notaSrv.save(n2);
            temaSrv.delete(2);
            stSrv.delete(2);
            notaSrv.delete("22");
            assert  stSrv.size() == 1;
            assert  temaSrv.size() == 1;
            assert  notaSrv.size() == 1;
        }
        catch (ValidationException e){
        }
    }

    /**
     * Tests for the update method in service
     */
    @Test
    public void update() {
        try{
            stSrv.save(s);
            temaSrv.save(t);
            notaSrv.save(n);
            Tema t2 = new Tema(1, 0,  6, "GUI");
            Student s2 = new Student(1,221, "Pop Ion","pop.ion@gmail.com","prof");
            Nota n2 = new Nota(1, 1, "prof", 10, "Irelevant");
            stSrv.save(s2);
            temaSrv.save(t2);
            notaSrv.save(n2);
            stSrv.update(s2);
            temaSrv.update(t2);
            notaSrv.update(n2);
            assert  stSrv.findOne(1).getGrupa()==221;
            assert  temaSrv.findOne(1).getDeadline()==8;
            assert  notaSrv.findOne("11").getFeedback().equals("Irelevant");

        }
        catch (ValidationException e){
        }
    }

    /**
     * Tests for the size method in service
     */
    @Test
    public void size() {
        try{
            stSrv.save(s);
            temaSrv.save(t);
            notaSrv.save(n);
            assert  stSrv.size()==1;
            assert  temaSrv.size() == 1;
            assert  notaSrv.size() == 1;
        }
        catch (ValidationException e){
        }
    }
}