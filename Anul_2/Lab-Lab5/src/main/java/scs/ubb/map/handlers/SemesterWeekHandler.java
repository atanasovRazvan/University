package scs.ubb.map.handlers;

import java.time.LocalDate;
import java.util.Calendar;

public class SemesterWeekHandler {
    private static SemesterWeekHandler instance;

    public SemesterWeekHandler() {
    }

    public static SemesterWeekHandler getInstance() {
        instance = instance == null ? new SemesterWeekHandler() : instance;
        return instance;
    }

    public int getWeekDeference(LocalDate startDate, LocalDate endDate) {
        Calendar beginningCalendar = Calendar.getInstance();
        beginningCalendar.set(startDate.getYear(), startDate.getMonthValue() - 1, startDate.getDayOfMonth());
        beginningCalendar.setFirstDayOfWeek(Calendar.MONDAY);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(endDate.getYear(), endDate.getMonthValue() - 1, endDate.getDayOfMonth());
        endCalendar.setFirstDayOfWeek(Calendar.MONDAY);

        return endCalendar.get(Calendar.WEEK_OF_YEAR) - beginningCalendar.get(Calendar.WEEK_OF_YEAR) + 1;
    }


}
