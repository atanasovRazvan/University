//package User_Interface;
//
//import Entities.Nota;
//import Entities.Student;
//import Entities.Tema;
//import Service.*;
//import java.util.Scanner;
//
//public class UI {
//
//    private StudentXMLService stSrv;
//    private TemaXMLService temeSrv;
//    private NotaXMLService noteSrv;
//
//    public UI(StudentXMLService stServ, TemaXMLService temeServ, NotaXMLService noteServ){
//
//        stSrv = stServ;
//        temeSrv = temeServ;
//        noteSrv = noteServ;
//
//    }
//
//    public void run() throws Exception {
//
//        Scanner S = new Scanner(System.in);
//        String cmd;
//        boolean OK = true;
//
//        while(OK){
//
//            System.out.println("1. Add student\n2. Add homework\n3. Update student\n4. Update homework\n5." +
//                    " Delete student\n6. Delete homework\n7. Show students\n8. Show homweorks\n9. Add grade\n10." +
//                    "Update grade\n11. Delete grade\n12. Show grades\n13. Filter students by group\n14. Filter students by homework\n" +
//                    "15. Filter by homework and teacher\n16. Filter grades by homework in a week");
//            System.out.println("Select the task (1-6 or X to stop)");
//            cmd = S.nextLine();
//            switch(cmd) {
//
//                case "1": {
//
//                    int idStudent, grupa;
//                    String nume, email, prof;
//                    System.out.println("ID-ul studentului: ");
//                    idStudent = S.nextInt();
//                    System.out.println("Grupa studentului: ");
//                    grupa = S.nextInt();
//                    S.nextLine();
//                    System.out.println("Numele studentului: ");
//                    nume = S.nextLine();
//                    System.out.println("Emailul studentului: ");
//                    email = S.nextLine();
//                    System.out.println("Profesorul studentului: ");
//                    prof = S.nextLine();
//                    Student student = new Student(idStudent, grupa, nume, email, prof);
//                    stSrv.save(student);
//
//                }
//                break;
//                case "2": {
//
//                    int nrTema, deadline;
//                    String descriere;
//                    System.out.println("ID-ul temei: ");
//                    nrTema = S.nextInt();
//                    System.out.println("Saptamana de deadline a temei ");
//                    deadline = S.nextInt(); S.nextLine();
//                    System.out.println("Descrierea temei ");
//                    descriere = S.nextLine();
//                    Tema tema = new Tema(nrTema, 0, deadline, descriere);
//                    temeSrv.save(tema);
//                    System.out.println(tema.getSaptPredare());
//
//                }
//                break;
//                case "3": {
//
//                    int idStudent, grupa;
//                    String nume, email, prof;
//                    System.out.println("ID-ul studentului: ");
//                    idStudent = S.nextInt();
//                    System.out.println("Grupa studentului: ");
//                    grupa = S.nextInt();
//                    S.nextLine();
//                    System.out.println("Numele studentului: ");
//                    nume = S.nextLine();
//                    System.out.println("Emailul studentului: ");
//                    email = S.nextLine();
//                    System.out.println("Profesorul studentului: ");
//                    prof = S.nextLine();
//                    Student student = new Student(idStudent, grupa, nume, email, prof);
//                    stSrv.update(student);
//
//                }
//                break;
//                case "4":{
//
//                    int nrTema, deadline;
//                    String descriere;
//                    System.out.println("ID-ul temei: ");
//                    nrTema = S.nextInt();
//                    System.out.println("Saptamana de deadline a temei ");
//                    deadline = S.nextInt();
//                    System.out.println("Descrierea temei ");
//                    descriere = S.nextLine();
//                    Tema tema = new Tema(nrTema, 0, deadline, descriere);
//                    temeSrv.update(tema);
//
//                } break;
//                case "5":{
//
//                    int idStudent;
//                    System.out.println("Introduceti ID-ul studentului: ");
//                    idStudent = S.nextInt();
//                    stSrv.delete(idStudent);
//
//                } break;
//                case "6":{
//
//                    int nrTema;
//                    System.out.println("Introduceti numarul temei: ");
//                    nrTema = S.nextInt();
//                    temeSrv.delete(nrTema);
//
//                } break;
//                case "7": stSrv.show(); break;
//                case "8": temeSrv.show(); break;
//                case "9": {
//
//                    int idStudent, idTema;
//                    String prof;
//                    int grade;
//                    String feedback;
//
//                    System.out.println("Introduceti ID-ul studentului: ");
//                    idStudent = S.nextInt(); S.nextLine();
//                    System.out.println("Introduceti numarul temei: ");
//                    idTema = S.nextInt(); S.nextLine();
//                    System.out.println("Numele profesorului: ");
//                    prof = S.nextLine();
//                    System.out.println("Introduceti nota: ");
//                    grade = S.nextInt(); S.nextLine();
//                    System.out.println("Feedbackul temei: ");
//                    feedback = S.nextLine();
//                    System.out.println("Cate saptamani ati intarziat cu postarea notei?");
//                    int intarziere = S.nextInt();
//                    S.nextLine();
//                    Nota nota = new Nota(idStudent, idTema, prof, grade, feedback);
//                    noteSrv.save(nota);
//                    nota.setGrade(noteSrv.getNotaCurenta(nota.getID(), temeSrv.findOne(idTema), intarziere));
//                    noteSrv.delete(nota.getID());
//                    noteSrv.save(nota);
//
//
//                } break;
//                case "10": {
//
//                    int idStudent, idTema;
//                    String prof;
//                    int grade;
//                    String feedback;
//
//                    System.out.println("Introduceti ID-ul studentului: ");
//                    idStudent = S.nextInt(); S.nextLine();
//                    System.out.println("Introduceti numarul temei: ");
//                    idTema = S.nextInt(); S.nextLine();
//                    System.out.println("Numele profesorului: ");
//                    prof = S.nextLine();
//                    System.out.println("Introduceti nota: ");
//                    grade = S.nextInt(); S.nextLine();
//                    System.out.println("Feedbackul temei: ");
//                    feedback = S.nextLine();
//                    Nota nota = new Nota(idStudent, idTema, prof, grade, feedback);
//                    noteSrv.update(nota);
//
//
//                } break;
//                case "11": {
//
//                    String idNota;
//                    System.out.println("Introduceti ID-ul notei (<<IDstudentIDnota>>): ");
//                    idNota = S.nextLine();
//                    noteSrv.delete(idNota);
//
//                } break;
//                case "12": noteSrv.show(); break;
//                case "13": {
//                    System.out.println("Introduceti numarul grupei: ");
//                    Integer grupa = S.nextInt(); S.nextLine();
//                    stSrv.filterByGroup(grupa).forEach(System.out::println);
//                } break;
//                case "14": {
//                    System.out.println("Introduceti numarul temei: ");
//                    Integer nrTema = S.nextInt(); S.nextLine();
//                    noteSrv.filterByHomework(nrTema, stSrv).forEach(System.out::println);
//                } break;
//                case "15": {
//                    System.out.println("Introduceti numarul temei: ");
//                    Integer nrTema = S.nextInt(); S.nextLine();
//                    System.out.println("Introduceti numarul profesorului: ");
//                    String prof = S.nextLine();
//                    noteSrv.filterByHomeworkByTeacher(nrTema, prof, stSrv).forEach(System.out::println);
//                } break;
//                case "16":{
//                    System.out.println("Introduceti numarul temei: ");
//                    Integer nrTema = S.nextInt(); S.nextLine();
//                    System.out.println("Introduceti numarul saptamanii: ");
//                    Integer saptPredata = S.nextInt(); S.nextLine();
//                    noteSrv.filterByHomeworkByDate(nrTema, saptPredata, noteSrv).forEach(System.out::println);
//                } break;
//                case "x": OK = false; break;
//                default: throw new Exception("Invalid selection");
//
//            }
//
//        }
//
//    }
//
//}
