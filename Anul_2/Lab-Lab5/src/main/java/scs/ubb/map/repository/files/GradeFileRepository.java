package scs.ubb.map.repository.files;

import scs.ubb.map.domain.Grade;
import scs.ubb.map.utils.Constants;
import scs.ubb.map.validators.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GradeFileRepository extends InFileRepository<String, Grade> {
    public GradeFileRepository(Validator<Grade> validator, String fileName) {
        super(validator, fileName);
    }

    @Override
    Grade getEntity(String line) {
        String[] lines = line.split(";");
        if (lines.length == 6) {
            Grade grade = new Grade(
                    lines[1],
                    Float.parseFloat(lines[2]),
                    LocalDate.parse(lines[3], DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)),
                    Long.parseLong(lines[4]),
                    Integer.parseInt(lines[5])
            );
            grade.setId(lines[0]);
            return grade;
        } else {
            //TODO Throw exception
        }

        return null;
    }

    @Override
    String getEntityString(Grade entity) {
        return entity.getId() + ";"
                + entity.getTeacher() + ";"
                + entity.getGrade() + ";"
                + entity.getDate().format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)) + ";"
                + entity.getStudentId() + ";"
                + entity.getHomeworkId();
    }
}
