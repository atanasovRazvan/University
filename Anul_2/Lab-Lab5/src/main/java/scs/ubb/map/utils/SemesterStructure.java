package scs.ubb.map.utils;

import scs.ubb.map.validators.repository.SemesterValidator;

import java.time.LocalDate;

public class SemesterStructure {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate holidayStartDate;
    private LocalDate holidayEndDate;

    private static SemesterStructure instance;

    public SemesterStructure(LocalDate startDate, LocalDate endDate,
                             LocalDate holidayStartDate, LocalDate holidayEndDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.holidayStartDate = holidayStartDate;
        this.holidayEndDate = holidayEndDate;
        new SemesterValidator().validate(this);
    }

    public static SemesterStructure getInstance() {
        instance = instance == null ? new SemesterStructure(null, null,
                null, null) : instance;
        return instance;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getHolidayStartDate() {
        return holidayStartDate;
    }

    public LocalDate getHolidayEndDate() {
        return holidayEndDate;
    }
}