package Entities;

import Utils.Pair;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StructuraAnUniv implements Entity<Integer> {

    private int year;
    private StructuraSemestru sem1;
    private StructuraSemestru sem2;

    public StructuraAnUniv(StructuraSemestru sem1, StructuraSemestru sem2){

        this.year = sem1.getStartDate().getYear();
        this.sem1 = sem1;
        this.sem2 = sem2;

    }

    /**
     * returns the current week number in the year structure
     * @return curentWeek - int
     */

    public Integer getCurrentWeek(){

        LocalDate current = LocalDate.now();
        int currentWeek;
        StructuraSemestru sem;
        if(current.isBefore(sem2.getStartDate())){
            sem=sem1;
        }
        else sem=sem2;

        currentWeek = (int) sem.getStartDate().until(current, ChronoUnit.WEEKS) + 1;

        Pair<LocalDate, LocalDate> holiday = sem.getHolidayWeeks();
        if(current.isAfter(holiday.second())){
            currentWeek -= holiday.first().until(holiday.second(), ChronoUnit.WEEKS) + 1;
        }

        return currentWeek;

    }

    /**
     * returns the year;
     * @return year - int
     */
    public int getYear() {
        return year;
    }

    /**
     * sets the year
     * @param year - int
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * returns the fist semester
     * @return sem1 - StructuraSemestru
     */
    public StructuraSemestru getSem1() {
        return sem1;
    }

    /**
     * sets the first semester
     * @param sem1 - StructuraSemestru
     */
    public void setSem1(StructuraSemestru sem1) {
        this.sem1 = sem1;
    }

    /**
     * returns the second semester
     * @return sem2 - StructuraSemestru
     */
    public StructuraSemestru getSem2() {
        return sem2;
    }

    /**
     * sets the second semester
     * @param sem2 - StructuraSemestru
     */
    public void setSem2(StructuraSemestru sem2) {
        this.sem2 = sem2;
    }

    /**
     * returns the year ID
     * @return year - int
     */
    @Override
    public Integer getID() {
        return this.year;
    }

    /**
     * sets the ID
     * @param year - integer
     */
    @Override
    public void setID(Integer year) {
        this.year = year;
    }
}
