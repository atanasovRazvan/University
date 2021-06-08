package Controllers;

import Entities.*;
import Repositories.NotaXMLRepository;
import Repositories.StudentXMLRepository;
import Repositories.TemaXMLRepository;
import Service.NotaXMLService;
import Service.StudentXMLService;
import Service.TemaXMLService;
import Utils.Pair;
import Validators.ValidationException;
import Validators.ValidatorNota;
import Validators.ValidatorStudent;
import Validators.ValidatorTema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GradeController {

    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private TableView table;
    @FXML
    private TableColumn nrTemaCol, numeCol, profCol, notaCol;
    @FXML
    private ComboBox comboBox;
    @FXML
    private TextField numeTextField, notaTextField, saptIntarziereTextField, profTextField;
    @FXML
    private TextArea feedbackTextField;
    @FXML
    private CheckBox motivareCheckBox, checkBox;
    private ValidatorNota v = new ValidatorNota();
    private ValidatorStudent vs = new ValidatorStudent();
    private NotaXMLRepository repo = new NotaXMLRepository(v);
    private StudentXMLRepository repos = new StudentXMLRepository(vs);
    private NotaXMLService srv = new NotaXMLService(repo);
    private StudentXMLService srvs = new StudentXMLService(repos);
    private ValidatorTema vt = new ValidatorTema();
    private TemaXMLRepository repot = new TemaXMLRepository(vt);
    private TemaXMLService srvt = new TemaXMLService(repot);
    private GradeForGUI nota;
    private Nota notaReal;

    // Trebuie sa fac un getter pentru nota sa il iau in controlleru de preview pentru completarea labelelor

    private int counter=0;

    private Pair<LocalDate, LocalDate> holidayWeeks_sem1 = new Pair(LocalDate.of(2019, 12, 23), LocalDate.of(2020, 1, 5));
    private StructuraSemestru sem1 = new StructuraSemestru(1, LocalDate.of(2019, 9, 30), 14, new Pair(LocalDate.of(2019, 12, 23), LocalDate.of(2020, 1, 5)));
    private StructuraSemestru sem2 = new StructuraSemestru(2, LocalDate.of(2020, 2, 24), 14, new Pair(LocalDate.of(2020, 4, 20), LocalDate.of(2020, 4, 26)));
    private StructuraAnUniv anul1 = new StructuraAnUniv(sem1, sem2);

    @FXML
    public void handlePreviewButton() throws Exception {

        Integer nrTema, grade, intarziereProf = 0;
        String numeStudent, prof;
        Boolean motivare;
        try {
            //if(comboBox.getValue().equals("")) throw new Exception("Trebuie sa selectati o tema!");
            //if(numeTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un nume!");
            //if(notaTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti o nota de la 1 la 10!");
            //if(profTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti numele dvs!");
            numeStudent = numeTextField.getText();
            grade = Integer.valueOf(notaTextField.getText());
            prof = profTextField.getText();
            String nrTemaString = comboBox.getSelectionModel().getSelectedItem().toString();
            nrTema = Integer.valueOf(nrTemaString.substring(nrTemaString.length() - 1));
            if (saptIntarziereTextField.isEditable() && !saptIntarziereTextField.getText().isEmpty())
                intarziereProf = Integer.valueOf(saptIntarziereTextField.getText());
            motivare = false;
            if (motivareCheckBox.isSelected()) {
                motivare = true;
                intarziereProf = -1;
            }
            Integer idStudent = 0;
            int index = 0;
            for (; index < srvs.size(); index++)
                if (srvs.findAll().get(index).getNume().equals(numeStudent)) {
                    idStudent = srvs.findAll().get(index).getID();
                    break;
                }
            String feedback = feedbackTextField.getText();
            nota = new GradeForGUI(nrTema, idStudent, numeStudent, prof, feedback, grade);
            if (idStudent == 0) throw new Exception("Studentul nu exista in baza de date!");
            notaReal = new Nota(idStudent, nrTema, prof, grade, feedback);
            srv.save(notaReal);
            notaReal.setGrade(srv.getNotaCurenta(nota.getID(), srvt.findOne(nrTema), intarziereProf));
            srv.delete(notaReal.getID());

            int penalizare = 0;
            if (motivare == false) {
                penalizare = anul1.getCurrentWeek() - srvt.findOne(nrTema).getDeadline() - intarziereProf;
            }

            feedback = "Ai intarziat cu predarea temei, deci vei fi depunctat cu "+penalizare+" puncte.\n" + feedback;
            feedbackTextField.setText(feedback);
            notaReal.setFeedback(feedback);

            String text;
            text = "Confirmarea notei pentru " + numeStudent + " la tema " + nrTema + ", nota " + notaReal.get_grade() + ".\n Motivare: " +
                    motivare + "\n Penalizare: " + penalizare;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, text, ButtonType.YES, ButtonType.NO);
            alert.setTitle("Adaugare nota");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES)
                srv.save(notaReal);
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
        loadData();
    }

    public GradeForGUI getNotaForGUI(){
        return nota;
    }

    public void saveNota() {
        try{srv.save(notaReal);}
        catch(Exception e){Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();}
    }

    @FXML
    public void handleDeleteButton() {
        try {
            Integer nrTema, idStudent = 0;
            String numeStudent;
            if (comboBox.getValue().equals("")) throw new Exception("Trebuie sa selectati o tema!");
            if (numeTextField.getText().equals("")) throw new Exception("Trebuie sa introduceti un nume!");
            numeStudent = numeTextField.getText();
            String nrTemaString = comboBox.getSelectionModel().getSelectedItem().toString();
            nrTema = Integer.valueOf(nrTemaString.substring(nrTemaString.length() - 1));
            int index = 0;
            for (; index < srvs.size(); index++)
                if (srvs.findAll().get(index).getNume().equals(numeStudent)) {
                    idStudent = srvs.findAll().get(index).getID();
                    break;
                }
            if (idStudent == 0) throw new Exception("Nu exista acest nume in baza de date!");
            srv.delete(idStudent.toString() + nrTema.toString());
        }
        catch(Exception e){Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();}
        loadData();
    }

    @FXML
    public void handleCheckBox(ActionEvent actionEvent){
        counter++;
        if(counter%2==1){
            saptIntarziereTextField.setDisable(false);
        }
        else {
            saptIntarziereTextField.setDisable(true);
        }
    }

    @FXML
    public void loadData(){
        nrTemaCol.setCellValueFactory(new PropertyValueFactory("idTema"));
        numeCol.setCellValueFactory(new PropertyValueFactory("numeStudent"));
        profCol.setCellValueFactory(new PropertyValueFactory("prof"));
        notaCol.setCellValueFactory(new PropertyValueFactory("nota"));
        List<GradeForGUI> list1;
        list1 = srv.findAll().stream().map(x-> new GradeForGUI(x.getTemaId(), x.getStudentId(),
                srvs.findOne(x.getStudentId()).getNume(),
                x.getProfesor(), x.getFeedback(), x.get_grade())).collect(Collectors.toList());
        ObservableList data1 = FXCollections.observableList(list1);
        table.setItems(data1);


        List<String> list = new ArrayList<>();
        srvt.findAll().forEach(x->list.add("Lab "+x.getID().toString()));
        ObservableList data = FXCollections.observableList(list);
        comboBox.setItems(data);
        int index = 0;
        for(; index < srvt.size(); index++)
            if(srvt.findAll().get(index).getDeadline().equals(anul1.getCurrentWeek())) break;
        comboBox.getSelectionModel().select(index);

        saptIntarziereTextField.setDisable(true);

    }

    @FXML
    public void initialize() throws ValidationException {
        for (Tema tema : srvt.findAll()) {
            if(anul1.getCurrentWeek() - tema.getDeadline() > 2)
                for(Student student : srvs.findAll()) {
                    Nota nota = new Nota(student.getID(), tema.getID(), "Insert name", 1, "Insert Feedback");
                    srv.save(nota);
                }
        }
        loadData();
    }

    @FXML
    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
