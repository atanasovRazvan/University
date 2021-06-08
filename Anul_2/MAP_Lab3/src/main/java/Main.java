import Repositories.*;
import Service.*;
import Validators.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main{

    public static void main(String[] args){

        //ValidatorStudent stVal = new ValidatorStudent();
        //ValidatorTema temaVal = new ValidatorTema();
        //ValidatorNota notaVal = new ValidatorNota();

        //StudentRepository stRepo = new StudentRepository(stVal);
        //TemeRepository temeRepo = new TemeRepository(temaVal);
        //NoteRepository noteRepo = new NoteRepository(notaVal);

        //StudentXMLRepository stRepo = new StudentXMLRepository(stVal);
        //TemaXMLRepository temeRepo = new TemaXMLRepository(temaVal);
        //NotaXMLRepository noteRepo = new NotaXMLRepository(notaVal);

        //StudentService stSrv = new StudentService(stRepo);
        //TemeService temeSrv = new TemeService(temeRepo);
        //NoteService noteSrv = new NoteService(noteRepo);

        //StudentXMLService stSrv = new StudentXMLService(stRepo);
        //TemaXMLService temeSrv = new TemaXMLService(temeRepo);
        //NotaXMLService noteSrv = new NotaXMLService(noteRepo);

        //UI console = new UI(stSrv, temeSrv, noteSrv);
        HelloFX.main(args);

    }
}