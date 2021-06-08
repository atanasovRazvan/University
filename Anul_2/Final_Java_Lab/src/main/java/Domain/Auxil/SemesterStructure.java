package Domain.Auxil;

import Domain.Entity;
import Utils.Pair;

import java.time.LocalDate;

/**
 * The structure of a semester at a university
 */
public class SemesterStructure extends Entity<Integer> {
    private LocalDate startDate;
    private int numberOfWeeks;
    private Pair<LocalDate, LocalDate> holidayWeeks;

    public SemesterStructure(int id, LocalDate startDate, int numberOfWeeks, Pair<LocalDate, LocalDate> holidayWeeks) {
        this.setId(id);
        this.startDate = startDate;
        this.numberOfWeeks = numberOfWeeks;
        this.holidayWeeks = holidayWeeks;
    }

    /**
     * returns the start date of the semester
     *
     * @return startDate - LocalDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * sets the start date of the semester
     *
     * @param startDate - LocalDate
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * returns the number of weeks of the Semester
     *
     * @return numberOfWeeks - int
     */
    public int getNumberOfWeeks() {
        return numberOfWeeks;
    }

    /**
     * sets the number of weeks of the semester
     *
     * @param numberOfWeeks - int
     */
    public void setNumberOfWeeks(int numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }

    /**
     * returns the holiday weeks as a pair of two dates
     *
     * @return holidayWeeks - pair of LocalDates - Pair
     */
    public Pair<LocalDate, LocalDate> getHolidayWeeks() {
        return holidayWeeks;
    }

    /**
     * sets the holiday weeks
     *
     * @param holidayWeeks - pair of LocalDates - Pair
     */
    public void setHolidayWeeks(Pair<LocalDate, LocalDate> holidayWeeks) {
        this.holidayWeeks = holidayWeeks;
    }
}