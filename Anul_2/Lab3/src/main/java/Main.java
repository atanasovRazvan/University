import Model.*;
import Repository.*;
import Validator.*;
import java.util.*;


public class Main {
    /**
     *
     * @return a repository for students
     */
    public static Repository<Integer,Student> createStudentRepo(){
        Validator<Student> v = new StudentValidator();
        Repository<Integer,Student> repo = new StudentRepository(v);
        try {
            repo.save(new Student(1,226,"Popescu Ion","popescu.ion@gmail.com","Serban Camelia"));
            repo.save(new Student(2,221,"Grigore Ana","grigore.ana@gmail.com","Serban Camelia"));
            repo.save(new Student(3,225,"Pop Andreea","pop.andreea@gmail.com","Serban Camelia"));
            repo.save(new Student(4,226,"Vasilescu Mara","vasilescu.mara@gmail.com","Serban Camelia"));

        }catch (ValidationException e){
            System.out.println(e.getMessage());
        }
        return repo;
    }

    /**
     *
     * @return a repository for homework
     */
    public static Repository<Integer,Tema> createTemaRepo(){
        Validator<Tema> v = new TemaValidator();
        Repository<Integer,Tema> repo = new TemaRepository(v);
        try {
            repo.save(new Tema(1,2,2,"laborator1"));
            repo.save(new Tema(2,3,2,"laborator2"));
            repo.save(new Tema(3,5,4,"laborator3"));
        }catch (ValidationException e){
            System.out.println(e.getMessage());
        }
        return repo;
    }

    public List<Student> filterStudents(Repository<Integer,Student> repo, int grupa){
        Iterable<Student> all = repo.findAll();
        List<Student> values = new ArrayList<Student>();
        for( Student s: all)
            if(s.getGrupa() == grupa)
                values.add(s);
        return values;
    }

    public List<Tema> filterTeme(Repository<Integer,Tema> repo, int deadline){
        Iterable<Tema> all = repo.findAll();
        List<Tema> values = new ArrayList<Tema>();
        for( Tema t: all)
            if(t.getDeadline() == deadline)
                values.add(t);
        return values;
    }

    /**
     * adds a new homework in repository
     * @param nrTema - integer, the ID of the homework, must not be null
     * @param deadline - integer, the deadline of the homework, must be between 1 and 14
     * @param saptPredare - integer, the presentation week of the homework, must be between 1 and 14
     * @param descriere - string, the description of the homework, must not be null
     */
    void addTema(Integer nrTema, Integer deadline, Integer saptPredare, String descriere, Repository<Integer,Tema> temaRepo){
        Tema t = new Tema(nrTema,deadline,saptPredare,descriere);
        Validator<Tema> v = new TemaValidator();
        try{
            v.validate(t);
            temaRepo.save(t);
        }
        catch (ValidationException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param t - an instance of class Tema
     * @param saptCurenta - integer between 1 and 14
     */
    void prelungireDeadline(Tema t, Integer saptCurenta){
        t.prelungireDeadline(saptCurenta);
    }

    public static void main(String[] args){
        Main app = new Main();

        //Repository<Integer,Student> studentRepo = createStudentRepo();
        //System.out.println(app.filterStudents(studentRepo,226));

        Repository<Integer,Tema> temaRepo = createTemaRepo();
        app.addTema(4, 6, 4, "lab3", temaRepo);
        System.out.println(temaRepo.findAll());

        Tema t = temaRepo.findOne(4);
        app.prelungireDeadline(t, 5);
        System.out.println(t.getDeadline());
    }
}
