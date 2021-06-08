package Entities;

import Utils.Pair;

import java.time.LocalDate;

public class Nota implements Entity<String> {

    private String id, profesor, Feedback;
    private LocalDate data;
    private int saptPredata;
    private int grade;
    private int idStud, idTem;

    public boolean isPredatLaTimp() {
        return predatLaTimp;
    }

    public void setPredatLaTimp(boolean predatLaTimp) {
        this.predatLaTimp = predatLaTimp;
    }

    private boolean predatLaTimp = true;

    private Pair<LocalDate, LocalDate> holidayWeeks_sem1 = new Pair(LocalDate.of(2019, 12, 23), LocalDate.of(2020, 1, 5));
    private StructuraSemestru sem1 = new StructuraSemestru(1, LocalDate.of(2019, 9, 30), 14, new Pair(LocalDate.of(2019, 12, 23), LocalDate.of(2020, 1, 5)));
    private StructuraSemestru sem2 = new StructuraSemestru(2, LocalDate.of(2020, 2, 24), 14, new Pair(LocalDate.of(2020, 4, 20), LocalDate.of(2020, 4, 26)));
    private StructuraAnUniv anul1 = new StructuraAnUniv(sem1, sem2);

    public Nota(int idStudent, int idTema, String prof, int nota, String feedback){

        this.id = String.valueOf(idStudent);
        this.id += String.valueOf(idTema);
        this.profesor = prof;
        this.grade = nota;
        this.data = LocalDate.now();
        this.saptPredata = anul1.getCurrentWeek();
        this.Feedback = feedback;
        this.idStud = idStudent; this.idTem = idTema;

    }

    /**
     * Returns the ID of the grade
     * @return id - String
     */
    @Override
    public String getID() {
        return this.id;
    }

    /**
     * Sets the ID of the grade
     * @param id - type String
     */
    @Override
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Returns the week for the grade
     * @return integer
     */
    public int getSaptPredata(){ return saptPredata; }

    /**
     * Sets the week for the grade
     * @param sapt - Integer
     */
    public void setSaptPredata(int sapt){ this.saptPredata = sapt; }

    /**
     * Returns the Feedback for the grade
     * @return String
     */
    public String getFeedback(){
        return Feedback;
    }

    /**
     * Sets the Feedback
     * @param feedback - String
     */
    public void setFeedback(String feedback){
        this.Feedback = feedback;
    }

    /**
     * Returns the professor
     * @return String
     */
    public String getProfesor() {
        return profesor;
    }

    /**
     * Sets the professor
     * @param profesor - String
     */
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    /**
     *  Returns the exact date
     * @return LocalDate
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Sets the date
     * @param data - LocalDate
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Returns the grade
     * @return integer
     */
    public int get_grade() {
        return grade;
    }

    /**
     * sets the grade
     * @param grade - Integer
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * Returns the ID of the student
     * @return Integer
     */
    public int getStudentId(){ return this.idStud; }

    /**
     * Returns the ID of the homework
     * @return Integer
     */
    public int getTemaId(){ return this.idTem; }

    /**
     * Returns the string version of the grade
     * Overrides the function toString
     * @return String
     */
    @Override
    public String toString(){

        return "{ID-ul notei:" + this.id
                + ", Profesor:" + this.profesor
                + ", Nota:" + this.grade
                + ", Feedback:" + this.Feedback
                + '\n';

    }

}
