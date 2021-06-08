package Entities;

import java.time.LocalDate;
import Utils.Pair;

public class StructuraSemestru implements Entity<Integer> {

    private int semestru;
    private LocalDate startDate;
    private int numberOfWeeks;
    private Pair<LocalDate, LocalDate> holidayWeeks;

    StructuraSemestru(){};

    public StructuraSemestru(int semestru, LocalDate startDate, int numberOfWeeks, Pair<LocalDate, LocalDate> holidayWeeks){

        this.setID(semestru);
        this.startDate = startDate;
        this.numberOfWeeks = numberOfWeeks;
        this.holidayWeeks = holidayWeeks;

    }


    /**
     * Returns the semester ID
     * @return semestru - Integer
     */
    @Override
    public Integer getID() {
        return this.semestru;
    }

    /**
     * Sets the semester ID
     * @param semestru - Integer
     */
    @Override
    public void setID(Integer semestru){
        this.semestru = semestru;
    }

    /**
     * Returns the current semester considered the ID
     * @return semestru - Integer
     */
    public int getSemestru() {
        return semestru;
    }

    /**
     * Sets the semester, considered the ID
     * @param semestru
     */
    public void setSemestru(int semestru) {
        this.semestru = semestru;
    }

    /**
     * returns the StartDate
     * @return startDate - Integer
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * sets the startDate
     * @param startDate
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * returns the numberOfWeeks
     * @return numberOfWeeks - integer
     */
    public int getNumberOfWeeks() {
        return numberOfWeeks;
    }

    /**
     * sets the numberOfWeeks
     * @param numberOfWeeks
     */
    public void setNumberOfWeeks(int numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }

    /**
     * returns the holidayWeeks
     * @return holidayWeeks - Pair<LocalDate, LocalDate>
     */
    public Pair<LocalDate, LocalDate> getHolidayWeeks() {
        return holidayWeeks;
    }

    /**
     * Sets the holidayWeeks
     * @param holidayWeeks
     */
    public void setHolidayWeeks(Pair<LocalDate, LocalDate> holidayWeeks) {
        this.holidayWeeks = holidayWeeks;
    }
}
