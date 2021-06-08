package scs.ubb.map.handlers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import scs.ubb.map.utils.Constants;
import scs.ubb.map.utils.SemesterStructure;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AcademicYearHandler {
    private String fileName;

    public AcademicYearHandler(String fileName) {
        this.fileName = fileName;
    }

    public SemesterStructure getSemesterWithDate(LocalDate date) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(fileName));

        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;

            LocalDate beginDate = LocalDate.parse((CharSequence) jsonObject.get("startDate"),
                    DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));
            LocalDate endDate = LocalDate.parse((CharSequence) jsonObject.get("endDate"),
                    DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));

            if (date.compareTo(beginDate) > 0 && date.compareTo(endDate) < 0) {
                LocalDate holidayBeginDate = LocalDate.parse((CharSequence) jsonObject.get("holidayStartDate"),
                        DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));
                LocalDate holidayEndDate = LocalDate.parse((CharSequence) jsonObject.get("holidayEndDate"),
                        DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));

                return new SemesterStructure(beginDate, endDate, holidayBeginDate, holidayEndDate);
            }
        }

        return null;
    }

    public SemesterStructure getCurrentSemester() throws IOException, ParseException {
        return getSemesterWithDate(LocalDate.now());
    }
}
