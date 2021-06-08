package Entities;

import java.time.LocalDate;
import Utils.*;

public class Tema implements Entity<Integer> {

    private Integer nrTema, deadline, saptPredare;
    private String descriere;

    private Pair<LocalDate, LocalDate> holidayWeeks_sem1 = new Pair(LocalDate.of(2019, 12, 23), LocalDate.of(2020, 1, 5));
    private StructuraSemestru sem1 = new StructuraSemestru(1, LocalDate.of(2019, 9, 30), 14, new Pair(LocalDate.of(2019, 12, 23), LocalDate.of(2020, 1, 5)));
    private StructuraSemestru sem2 = new StructuraSemestru(2, LocalDate.of(2020, 2, 24), 14, new Pair(LocalDate.of(2020, 4, 20), LocalDate.of(2020, 4, 26)));
    private StructuraAnUniv anul1 = new StructuraAnUniv(sem1, sem2);

    /**
     * Constructor for class Tema
     * @param nrTema - integer, the ID of the homework, must not be null
     * @param deadline - integer, the deadline of the homework, must be between 1 and 14
     * @param descriere - string, the description of the homework, must not be null
     */
    public Tema(Integer nrTema, Integer startWeek, Integer deadline, String descriere) {
        this.nrTema = nrTema;
        this.deadline = deadline;
        if(startWeek==0) this.saptPredare = anul1.getCurrentWeek();
        else this.saptPredare = startWeek;
        this.descriere = descriere;
    }

    /**
     * getter for ID
     * @return the homework ID - integer not null
     */
    @Override
    public Integer getID() {
        return nrTema;
    }

    /**
     * setter for ID
     * @param nrTema - integer, the ID of the homework, must not be null
     */
    @Override
    public void setID(Integer nrTema) {
        this.nrTema = nrTema;
    }

    /**
     * getter for deadline
     * @return the homework deadline - integer between 1 and 14
     */
    public Integer getDeadline() {
        return deadline;
    }

    /**
     * setter for deadline
     * @param deadline - integer, the deadline of the homework, must be between 1 and 14
     */
    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    /**
     * getter for presentation week
     * @return the homework presentation week - integer between 1 and 14
     */
    public Integer getSaptPredare() {
        return saptPredare;
    }

    /**
     * Sets the presentation week
     * @param SaptPredare - Integer
     */
    public void setSaptPredare(Integer SaptPredare){
        this.saptPredare = SaptPredare;
    }

    /**
     * getter for description
     * @return the homework description - string not null
     */
    public String getDescriere() {
        return descriere;
    }

    /**
     * setter for description
     * @param descriere - string, the description of the homework, must not be null
     */
    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    /**
     *
     * @return the string for a homework
     */
    @Override
    public String toString() {
        return "Tema{" +
                "nrTema=" + nrTema +
                ", deadline=" + deadline +
                ", saptPredare=" + saptPredare +
                ", descriere='" + descriere + '\'' +
                '}' + '\n';
    }

}
