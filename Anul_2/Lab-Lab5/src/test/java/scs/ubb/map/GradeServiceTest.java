package scs.ubb.map;

import org.junit.Test;
import scs.ubb.map.domain.Grade;
import scs.ubb.map.repository.CrudRepository;
import scs.ubb.map.repository.files.GradeFileRepository;
import scs.ubb.map.repository.files.HomeworkFileRepository;
import scs.ubb.map.repository.files.StudentFileRepository;
import scs.ubb.map.repository.files.json.GradeJSONRepository;
import scs.ubb.map.repository.files.json.JSONRepository;
import scs.ubb.map.services.config.Config;
import scs.ubb.map.services.service.GradeService;
import scs.ubb.map.services.service.HomeworkService;
import scs.ubb.map.services.service.Service;
import scs.ubb.map.services.service.StudentService;
import scs.ubb.map.utils.AcademicYear;
import scs.ubb.map.validators.ValidationException;
import scs.ubb.map.validators.repository.GradeValidator;
import scs.ubb.map.validators.repository.HomeworkValidator;
import scs.ubb.map.validators.repository.StudentValidator;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GradeServiceTest {
    private AcademicYear academicYear = new AcademicYear(Config.getProperties().getProperty("year-data"));
    private CrudRepository studentRepo = new StudentFileRepository(new StudentValidator(),
            Config.getProperties().getProperty("student-data"));
    private CrudRepository homeworkRepo = new HomeworkFileRepository(new HomeworkValidator(),
            Config.getProperties().getProperty("homework-data"));
    private CrudRepository gradeRepo = new GradeFileRepository(new GradeValidator(studentRepo, homeworkRepo),
            Config.getProperties().getProperty("grade-data"));

    private JSONRepository jsonRepository = new GradeJSONRepository("data/studentsGrades/");

    private Service studentService = new StudentService(studentRepo);
    private Service homeworkService = new HomeworkService(homeworkRepo);
    private Service gradeService = new GradeService(gradeRepo, jsonRepository);

    @Test(expected = ValidationException.class)
    public void testSaveGradeWithInvalidGradeShouldThrowException() {
        Grade grade = new Grade("Nicu", 11L, LocalDate.now(), 1L, 2);
        gradeService.save(grade);
    }

    @Test(expected = ValidationException.class)
    public void testSaveGradeToANonExistingStudentShouldThrowException() {
        Grade grade = new Grade("Nicu", 10L, LocalDate.now(), 10L, 2);
        gradeService.save(grade);
    }

    @Test(expected = ValidationException.class)
    public void testSaveGradeWithANonExistingHomeworkShouldThrowException() {
        Grade grade = new Grade("Nicu", 10L, LocalDate.now(), 1L, 20);
        gradeService.save(grade);
    }

    @Test
    public void testSaveValidGradeShouldReturnNull() {
        Grade grade = new Grade("Nicu", 10L, LocalDate.now(), 1L, 2);
        assertNull(gradeService.save(grade));
        assertEquals(grade, gradeService.delete(grade.getId()));
    }

    @Test
    public void testTestSaveGradeWithJson() {
        Grade grade = new Grade("Alice", 9L, LocalDate.now(), 3L, 2);
        assertNull(((GradeService)gradeService).save(grade,
                (StudentService) studentService,
                (HomeworkService) homeworkService,
                "Feedback"));
    }
}
