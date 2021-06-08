package Entities;

public class DTOMediaLab {

    private Integer idStudent;
    private Double pondere, nota;
    private String numeStudent;

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public Double getPondere() {
        return pondere;
    }

    public void setPondere(Double pondere) {
        this.pondere = pondere;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public DTOMediaLab(Integer idStud, String nume, Double ponder, Double not){
        numeStudent = nume;
        idStudent = idStud;
        pondere = ponder;
        nota = not;
    }

    public Double get_media(){
        return nota/pondere;
    }

    @Override
    public String toString(){
        return "Nume: "+numeStudent+" | "+"Media: "+this.get_media();
    }

}
