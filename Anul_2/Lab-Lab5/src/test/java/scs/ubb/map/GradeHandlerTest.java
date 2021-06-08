package scs.ubb.map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import scs.ubb.map.domain.Grade;
import scs.ubb.map.handlers.GradeHandler;
import scs.ubb.map.repository.CrudRepository;
import scs.ubb.map.repository.files.HomeworkFileRepository;
import scs.ubb.map.repository.files.json.GradeJSONRepository;
import scs.ubb.map.repository.files.json.JSONRepository;
import scs.ubb.map.services.config.Config;
import scs.ubb.map.services.service.HomeworkService;
import scs.ubb.map.services.service.Service;
import scs.ubb.map.utils.AcademicYear;
import scs.ubb.map.validators.repository.HomeworkValidator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class GradeHandlerTest {
    private CrudRepository homeworkRepo = new HomeworkFileRepository(new HomeworkValidator(),
            Config.getProperties().getProperty("homework-data"));

    private JSONRepository jsonRepository = new GradeJSONRepository("data/studentsGrades/");

    private Service homeworkService = new HomeworkService(homeworkRepo);
    private GradeHandler gradeHandler = new GradeHandler((HomeworkService) homeworkService);
    private AcademicYear academicYear = new AcademicYear(Config.getProperties().getProperty("year-data"));

    @Test
    public void testSaveGradeAfterTwoWeeksShouldGiveGrade1() {
        Grade grade = new Grade("", 10, LocalDate.of(2019, 11, 20),
                1L, 1);
        grade = gradeHandler.getGradeWithConstraints(grade, new HashMap<>());
        assertEquals(1, grade.getGrade(),0);
    }

    @Test
    public void testSaveGradeAfterOneAbsenceShouldReduceGradeWithOnePoint() {
        Grade grade = new Grade("", 10, LocalDate.of(2019, 11, 13),
                1L, 5);
        grade = gradeHandler.getGradeWithConstraints(grade, new HashMap<>());
        assertEquals(9L, grade.getGrade(), 0);
    }

    @Test
    public void testSaveGradeAfterOneAbsenceWithMotivation_Should_NotChangeTheGrade() {
        Grade grade = new Grade("", 10, LocalDate.of(2019, 11, 13),
                1L, 5);
        Map<String, Object> contraints = new HashMap<>();
        contraints.put("motivated_absence", 1);

        grade = gradeHandler.getGradeWithConstraints(grade, contraints);
        assertEquals(10L, grade.getGrade(), 0);
    }

    @Test
    public void testSaveGradeAfterTwoAbsencesWithOneWeekMotivationShouldReduceGradeWithOnePoint() {
        Grade grade = new Grade("", 10, LocalDate.of(2019, 11, 13),
                1L, 6);
        Map<String, Object> contraints = new HashMap<>();
        contraints.put("motivated_absence", 1);

        grade = gradeHandler.getGradeWithConstraints(grade, contraints);
        assertEquals(9L, grade.getGrade(), 0);
    }

    @Test
    public void testSaveGradeAtDeadlineShouldNotChangeTheGrade() {
        Grade grade = new Grade("", 10, LocalDate.of(2019, 11, 13),
                1L, 7);

        grade = gradeHandler.getGradeWithConstraints(grade, new HashMap<>());
        assertEquals(10L, grade.getGrade(), 0);

    }
}
