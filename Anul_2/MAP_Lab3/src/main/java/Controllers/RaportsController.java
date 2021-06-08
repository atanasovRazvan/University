package Controllers;

import Entities.DTOLabGreu;
import Entities.DTOMediaLab;
import Entities.Nota;
import Entities.Student;
import Repositories.NotaXMLRepository;
import Repositories.StudentXMLRepository;
import Repositories.TemaXMLRepository;
import Service.NotaXMLService;
import Service.StudentXMLService;
import Service.TemaXMLService;
import Validators.ValidatorNota;
import Validators.ValidatorStudent;
import Validators.ValidatorTema;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RaportsController {

    @FXML
    private Button closeButton;
    @FXML
    private ComboBox comboBox;
    @FXML
    private ListView listView;
    private ValidatorStudent vs = new ValidatorStudent();
    private ValidatorTema vt = new ValidatorTema();
    private ValidatorNota vn = new ValidatorNota();
    private StudentXMLRepository repos = new StudentXMLRepository(vs);
    private TemaXMLRepository repot = new TemaXMLRepository(vt);
    private NotaXMLRepository repon = new NotaXMLRepository(vn);
    private StudentXMLService srvs = new StudentXMLService(repos);
    private TemaXMLService srvt = new TemaXMLService(repot);
    private NotaXMLService srvn = new NotaXMLService(repon);

    @FXML
    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(){
        List<String> list = new ArrayList<>();
        list.add("Media studentilor");
        list.add("Cel mai greu laborator");
        list.add("Studentii cu media >= 4");
        list.add("Studentii harnici");
        ObservableList data = FXCollections.observableList(list);
        comboBox.setItems(data);
        comboBox.getSelectionModel().select(list.get(0));

    }

    @FXML
    public void handleDoButton(){
        String s = comboBox.getSelectionModel().getSelectedItem().toString();
        if(s.equals("Media studentilor")) mediaLab();
        if(s.equals("Cel mai greu laborator")) celMaiGreuLab();
        if(s.equals("Studentii cu media >= 4")) studentiMedia4();
        if(s.equals("Studentii harnici")) studentiHarnici();
    }

    private List<DTOMediaLab> get_media_studenti(){
        int index = 0;
        List<DTOMediaLab> list = new ArrayList<>();
        for(; index < srvn.size(); index++){
            Nota nota = srvn.findAll().get(index);
            int index2 = 0, ok = 0;
            for(; index2 < list.size(); index2++)
                if(list.get(index2).getIdStudent() == nota.getStudentId()) {
                    double d1 = srvt.findOne(nota.getTemaId()).getDeadline() - srvt.findOne(nota.getTemaId()).getSaptPredare();
                    list.get(index2).setPondere(list.get(index2).getPondere() + d1);
                    list.get(index2).setNota(list.get(index2).getNota() + nota.get_grade()*d1);
                    ok = 1;
                    break;
                }

            if(ok == 0) {
                double d1 = srvt.findOne(nota.getTemaId()).getDeadline() - srvt.findOne(nota.getTemaId()).getSaptPredare();
                DTOMediaLab dto = new DTOMediaLab(nota.getStudentId(), srvs.findOne(nota.getStudentId()).getNume() ,d1, (double) nota.get_grade());
                list.add(dto);
            }
        }
        return list;
    }

    @FXML
    private void mediaLab(){

        // nume student si media

        List<String> lista = new ArrayList<>();

        int index = 0;
        for(; index < get_media_studenti().size(); index++){
            lista.add(get_media_studenti().get(index).toString());
        }
        ObservableList data = FXCollections.observableList(lista);
        listView.setItems(data);

    }

    private void celMaiGreuLab(){

        List<DTOLabGreu> list = new ArrayList<>();
        int index = 0;
        for(; index < srvn.size(); index++) {
            Nota nota = srvn.findAll().get(index);
            int index2 = 0, w = 0;
            for(; index2 < list.size(); index2++)
                if(list.get(index2).getNrTema() == nota.getTemaId()) {
                    list.get(index2).setNota(list.get(index2).getNota() + nota.get_grade());
                    list.get(index2).setCounter();
                    w=1;
                    break;
                }
            if(w==0){
                DTOLabGreu dto = new DTOLabGreu((double) nota.get_grade(), nota.getTemaId());
                list.add(dto);
            }
        }

        index = 0;
        double media_min = 11, pos = 0;
        for(; index < list.size(); index++)
            if(list.get(index).getMedia() < media_min){
                media_min = list.get(index).getMedia();
                pos = index;
            }

        List<String> lista = new ArrayList<>(); lista.add(list.get((int) pos).toString());
        ObservableList data = FXCollections.observableList(lista);
        listView.setItems(data);

    }

    private void studentiMedia4(){
        List<DTOMediaLab> list = get_media_studenti();
        List<String> lista = new ArrayList<>();
        int index = 0;
        for(; index < list.size(); index++){
            if(list.get(index).get_media() >= 4)
                lista.add(list.get(index).toString());
        }


        ObservableList data = FXCollections.observableList(lista);
        listView.setItems(data);
    }

    private void studentiHarnici(){
        List<String> list = new ArrayList<>();
        List<Integer> listaSt = new ArrayList<>();
        int index = 0;
        for(; index<srvs.size(); index++)
            listaSt.add(srvs.findAll().get(index).getID());

        index = 0;
        for(; index < srvn.size(); index++)
            if(!srvn.findAll().get(index).isPredatLaTimp())
                listaSt.remove(srvn.findAll().get(index).getStudentId());

        index = 0;
        for(; index < listaSt.size(); index++)
            list.add(srvs.findOne(listaSt.get(index)).getNume());

        ObservableList data = FXCollections.observableList(list);
        listView.setItems(data);
    }

}
