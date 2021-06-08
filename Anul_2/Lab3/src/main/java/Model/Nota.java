package Model;

public class Nota {
    private Double nota;
    private Student s;
    private Tema t;

    public Nota(Double nota, Student s, Tema t) {
        this.nota = nota;
        this.s = s;
        this.t = t;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Student getStudent() {
        return s;
    }

    public void setStudent(Student s) {
        this.s = s;
    }

    public Tema getTema() {
        return t;
    }

    public void setTema(Tema t) {
        this.t = t;
    }

    public void intarziere(Double nota, Tema t){
        if(t.getSaptPredare() == t.getDeadline() + 1)
            setNota(nota - 2.5);
    }
}
