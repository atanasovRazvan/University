package Service;

import Entities.Nota;
import Repositories.NoteRepository;
import Repositories.StudentRepository;
import Repositories.TemeRepository;
import Validators.*;

import Entities.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class TestNoteService {

    private Validator<Student> v = new ValidatorStudent();
    private Validator<Tema> vt = new ValidatorTema();
    private Validator <Nota> vn = new ValidatorNota();
    private StudentRepository repo = new StudentRepository(v);
    private TemeRepository repot = new TemeRepository(vt);
    private NoteRepository repon = new NoteRepository(vn);
    private StudentService stSrv = new StudentService(repo);
    private TemeService temaSrv = new TemeService(repot);
    private NoteService notaSrv = new NoteService(repon);
    private Student s1 = new Student(1,226, "Pop Ion","pop.ion@gmail.com","prof");
    private Student s2 = new Student(2,227, "Pop Ion","pop.ion@gmail.com","prof");
    private Student s3 = new Student(3,228, "Pop Ion","pop.ion@gmail.com","prof");
    private Tema t1 = new Tema(1, 1,2,"Unittesting");
    private Tema t2 = new Tema(1, 2,3,"Unittesting2");
    private Tema t3 = new Tema(1, 3,4,"Unittesting3");
    private Nota n1 = new Nota(1, 1, "prof", 8, "Lipseste arhitectura stratificata!");
    private Nota n2 = new Nota(1, 2, "prof", 8, "Lipseste arhitectura stratificata2!");
    private Nota n3 = new Nota(2, 2, "prof", 8, "Lipseste arhitectura stratificata3!");

    /**
     * Tests for the filterByHomework method
     * @throws ValidationException
     */
//    public void filterByHomework() throws ValidationException {
//
//        stSrv.save(s1); stSrv.save(s2); stSrv.save(s3);
//        temaSrv.save(t1); temaSrv.save(t2); temaSrv.save(t2);
//        notaSrv.save(n1); notaSrv.save(n2); notaSrv.save(n3);
//        assert notaSrv.filterByHomework(1, stSrv).size() == 1;
//
//    }

    /**
     * Tests for the filterByHomeworkByTeacher method
     * @throws ValidationException
     */
//    public void filterByHomeworkByTeacher() throws ValidationException {
//
//        stSrv.save(s1); stSrv.save(s2); stSrv.save(s3);
//        temaSrv.save(t1); temaSrv.save(t2); temaSrv.save(t2);
//        notaSrv.save(n1); notaSrv.save(n2); notaSrv.save(n3);
//        assert notaSrv.filterByHomeworkByTeacher(1, "prof", stSrv).size() == 1;
//
//    }

    /**
     * Tests for the filterByHomeworkByDate method
     * @throws ValidationException
     */
//    public void filterByHomeworkByDate() throws ValidationException {
//
//        stSrv.save(s1); stSrv.save(s2); stSrv.save(s3);
//        temaSrv.save(t1); temaSrv.save(t2); temaSrv.save(t2);
//        notaSrv.save(n1); notaSrv.save(n2); notaSrv.save(n3);
//        n3.setSaptPredata(2);
//        assert notaSrv.filterByHomeworkByDate(1, 2, notaSrv).size() == 0;
//
//    }

    /**
     * Tests for the filterByGroup method
     * @throws ValidationException
     */
//    @Test
//    public void filterByGroup() throws ValidationException {
//
//        stSrv.save(s1); stSrv.save(s2); stSrv.save(s3);
//        temaSrv.save(t1); temaSrv.save(t2); temaSrv.save(t2);
//        notaSrv.save(n1); notaSrv.save(n2); notaSrv.save(n3);
//        assert stSrv.filterByGroup(226).size()==1;
//
//    }

}
