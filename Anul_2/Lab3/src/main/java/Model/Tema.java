package Model;


public class Tema implements HasID<Integer>{
    private Integer nrTema, deadline, saptPredare;
    private String descriere;

    /**
     * Constructor for class Tema
     * @param nrTema - integer, the ID of the homework, must not be null
     * @param deadline - integer, the deadline of the homework, must be between 1 and 14
     * @param saptPredare - integer, the presentation week of the homework, must be between 1 and 14
     * @param descriere - string, the description of the homework, must not be null
     */
    public Tema(Integer nrTema, Integer deadline, Integer saptPredare, String descriere) {
        this.nrTema = nrTema;
        this.deadline = deadline;
        this.saptPredare = saptPredare;
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
     * setter for presentation week
     * @param saptPredare - integer, the presentation week of the homework, must be between 1 and 14
     */
    public void setSaptPredare(Integer saptPredare) {
        this.saptPredare = saptPredare;
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

    /**
     * extends the deadline of a homework if the current week is lower than the deadline
     * @param saptCurenta - integer between 1 and 14
     */
    public void prelungireDeadline(Integer saptCurenta){
        if(saptCurenta < this.getDeadline())
            setDeadline(this.getDeadline() + 1);
    }
}
