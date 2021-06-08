package Domain.Auxil;

import Domain.Entity;
import Utils.Pair;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * The year structure at an university
 */
public class YearStructure extends Entity<Integer> {
    private int year;
    private SemesterStructure sem1;
    private SemesterStructure sem2;

    private static YearStructure instance;

    private YearStructure(SemesterStructure sem1, SemesterStructure sem2) {
        this.year = sem1.getStartDate().getYear();
        this.sem1 = sem1;
        this.sem2 = sem2;
    }

    public static YearStructure getInstance() throws IllegalArgumentException {
        if (instance == null)
            throw new IllegalArgumentException("Structura anului nu a fost inca instantiata.");
        return instance;
    }

    public static YearStructure getInstance(SemesterStructure sem1, SemesterStructure sem2) {
        if (instance == null) {
            instance = new YearStructure(sem1, sem2);
            return instance;
        }
        instance.setSem1(sem1);
        instance.setSem2(sem2);
        instance.setYear(sem1.getStartDate().getYear());
        return instance;
    }

    /**
     * returns the current week number in the year structure
     *
     * @return currentWeekNumber - int
     */
    public Integer getCurrentWeek(LocalDate current) {
        int currentWeekNumber;
        SemesterStructure sem;

        assert current != null;
        if (current.isBefore(sem2.getStartDate())) {
            sem = sem1;
        } else {
            sem = sem2;
        }

        currentWeekNumber = (int) sem.getStartDate().until(current, ChronoUnit.WEEKS) + 1;

        Pair<LocalDate, LocalDate> holiday = sem.getHolidayWeeks();
        if (current.isAfter(holiday.getSecond())) {
            currentWeekNumber -= holiday.getFirst().until(holiday.getSecond(), ChronoUnit.WEEKS) + 1;
        }

        return currentWeekNumber;
    }

    /**
     * returns the year of the year structure
     *
     * @return year - int
     */
    public int getYear() {
        return year;
    }

    /**
     * sets the year of the year structure
     *
     * @param year - int
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * returns the first semester structure
     *
     * @return sem1 - SemesterStructure
     */
    public SemesterStructure getSem1() {
        return sem1;
    }

    /**
     * sets the first semester structure
     *
     * @param sem1 SemesterStructure
     */
    public void setSem1(SemesterStructure sem1) {
        this.sem1 = sem1;
    }

    /**
     * returns the second semester structure
     *
     * @return sem2 - SemesterStructure
     */
    public SemesterStructure getSem2() {
        return sem2;
    }

    /**
     * sets the second semester structure
     *
     * @param sem2 - SemesterStructure
     */
    public void setSem2(SemesterStructure sem2) {
        this.sem2 = sem2;
    }
}