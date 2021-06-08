package Entities;

public class GradeForGUI implements Entity<String> {

    private Integer nota, idTema, idStudent;
    private String prof, feedback, numeStudent;

    @Override
    public String toString() {
        return "GradeForGUI{" +
                "nota=" + nota +
                ", idTema=" + idTema +
                ", idStudent=" + idStudent +
                ", prof='" + prof + '\'' +
                ", feedback='" + feedback + '\'' +
                ", numeStudent='" + numeStudent + '\'' +
                '}';
    }

    public GradeForGUI(Integer idTemaS, Integer idStudentS, String numeStudentS, String profS, String feedbackS, Integer notaS){
        idTema = idTemaS;
        idStudent = idStudentS;
        numeStudent = numeStudentS;
        prof = profS;
        feedback = feedbackS;
        nota = notaS;
    }

    @Override
    public String getID() { return idStudent.toString()+idTema.toString(); }

    @Override
    public void setID(String s) { }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Integer getIdTema() {
        return idTema;
    }

    public void setIdTema(Integer idTema) {
        this.idTema = idTema;
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getNumeStudent() {
        return numeStudent;
    }

    public void setNumeStudent(String numeStudent) {
        this.numeStudent = numeStudent;
    }
}
