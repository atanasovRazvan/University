package scs.ubb.map.utils;

import org.json.simple.parser.ParseException;
import scs.ubb.map.handlers.AcademicYearHandler;
import scs.ubb.map.handlers.SemesterWeekHandler;

import java.io.IOException;
import java.time.LocalDate;

public class AcademicYear {
    private SemesterStructure semesterStructure;
    private static AcademicYear instance;

    public AcademicYear(String academicYearFile) {
        try {
            this.semesterStructure = new AcademicYearHandler(academicYearFile).getCurrentSemester();
            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static AcademicYear getInstance() {
        return instance;
    }

    public int getCurrentWeek() {
        return getSemesterWeek(LocalDate.now());
    }

    public int getSemesterWeek(LocalDate date) {
        if (semesterStructure.getHolidayStartDate().compareTo(date) < 0 &&
                semesterStructure.getHolidayEndDate().compareTo(date) > 0) {
            return -1;
        }

        if (semesterStructure.getStartDate().compareTo(date) > 0 || semesterStructure.getEndDate().compareTo(date) < 0) {
            return -1;
        }

        if (semesterStructure.getHolidayStartDate().compareTo(date) > 0) {
            return SemesterWeekHandler.getInstance().getWeekDeference(semesterStructure.getStartDate(), date);
        }

        if (semesterStructure.getHolidayStartDate().getYear() == date.getYear()
                && semesterStructure.getHolidayEndDate().getYear() == date.getYear()) {
            return SemesterWeekHandler.getInstance().getWeekDeference(semesterStructure.getStartDate(), date) -
                    SemesterWeekHandler.getInstance().getWeekDeference(semesterStructure.getHolidayStartDate(),
                            semesterStructure.getHolidayEndDate());

        }

        return SemesterWeekHandler.getInstance().getWeekDeference(semesterStructure.getStartDate(),
                semesterStructure.getHolidayStartDate()) +
                SemesterWeekHandler.getInstance().getWeekDeference(semesterStructure.getHolidayEndDate(), date) + 1;
    }
}
