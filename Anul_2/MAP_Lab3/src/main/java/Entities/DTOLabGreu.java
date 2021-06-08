package Entities;

public class DTOLabGreu {

    public Double getCounter() {
        return counter;
    }

    public void setCounter() {
        this.counter += 1;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    private Double nota;
    private Double counter;
    private Integer nrTema;

    public Integer getNrTema() {
        return nrTema;
    }

    public void setNrTema(Integer nrTema) {
        this.nrTema = nrTema;
    }

    public DTOLabGreu(Double notaa, Integer nrTemaa){
        nota = notaa;
        counter = (double) 1;
        nrTema = nrTemaa;
    }

    public double getMedia(){
        return nota/counter;
    }

    @Override
    public String toString(){
        return "Cea mai grea tema este LAB "+nrTema+" cu media "+nota/counter;
    }
}
