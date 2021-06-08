package Service;

import Entities.Nota;
import Entities.Student;
import Entities.Tema;
import Repositories.NoteRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class NoteService extends AbstractService<String, Nota> {

    public NoteService(NoteRepository repo){
        super(repo);
    }

    /**
     * Returneaza nota maxima care poate fi obtinuta
     * @param idNota - string
     * @param tema - entitate de tip Tema
     * @return Integer
     */
    public int getNotaMaxima(String idNota, Tema tema){

        int delay = super.findOne(idNota).getSaptPredata() - tema.getDeadline();
        if(delay>0)
            return 10 - (super.findOne(idNota).getSaptPredata() - tema.getDeadline());
        return 10;

    }

    /**
     * Returneaza nota reala pe care o primeste studentul
     * @param idNota - id-ul notei
     * @param tema - tema la care se pune nota
     * @param intarziere - nr de saptamani cu care a intarziat profesorul
     * @return integer
     */
    public int getNotaCurenta(String idNota, Tema tema, int intarziere){

        int deadline = tema.getDeadline();
        int delay = super.findOne(idNota).getSaptPredata() - deadline;
        if(delay>0)
            return super.findOne(idNota).get_grade() - (super.findOne(idNota).getSaptPredata() - deadline) + intarziere;
        return super.findOne(idNota).get_grade() + intarziere;

    }

    /**
     * Returneaza raportul cerut, studentii care au rezolvat o anumita tema
     * @param nrTema - integer
     * @param stSrv - referinta pentru service-ul de studenti
     * @return List<Student>
     */
//    public List<Student> filterByHomework(Integer nrTema, Service<Integer, Student> stSrv){
//        return this.getAll().stream()
//                .filter(x->x.getTemaId()==nrTema)
//                .map(x->stSrv.findOne(x.getStudentId())).collect(Collectors.toList());
//    }

    /**
     * Returneaza raportul cerut, studentii care au rezolvat o anumita tema si i-au predat-o unui anumit profesor
     * @param nrTema - Integer
     * @param prof - String
     * @param stSrv - referinta pentru service-ul de studenti
     * @return List<Student>
     */
//    public List<Student> filterByHomeworkByTeacher(Integer nrTema, String prof, Service<Integer, Student> stSrv){
//        return this.getAll().stream()
//                .filter(x->x.getTemaId()==nrTema)
//                .filter(x->x.getProfesor().equals(prof))
//                .map(x->stSrv.findOne(x.getStudentId())).collect(Collectors.toList());
//    }

    /**
     * Returneaza raportul cerut, notele obtinute la o anumita tema intr-o anumita saptamana
     * @param nrTema - Integer
     * @param saptamana - Integer
     * @param notaSrv - referinta pentru service-ul de note
     * @return List<Student>
     */
//    public List<Nota> filterByHomeworkByDate(Integer nrTema, Integer saptamana, Service<String, Nota> notaSrv){
//
//        return this.getAll().stream()
//                .filter(x->x.getTemaId()==nrTema)
//                .filter(x->x.getSaptPredata()==saptamana)
//                .collect(Collectors.toList());
//
//    }

}
