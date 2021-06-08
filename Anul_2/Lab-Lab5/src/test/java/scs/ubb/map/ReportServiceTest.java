package scs.ubb.map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
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
import scs.ubb.map.utils.ReportsService;
import scs.ubb.map.validators.repository.GradeValidator;
import scs.ubb.map.validators.repository.HomeworkValidator;
import scs.ubb.map.validators.repository.StudentValidator;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ReportServiceTest {
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

    private ReportsService reportsService = new ReportsService(studentService, homeworkService, gradeService);

    @Test
    public void testGetStudentsByGroup() {
        assertEquals(3, reportsService.getStudentFromGroup(223).size());
    }

    @Test
    public void testGetStudentWithHomework() {
        assertEquals(4, reportsService.getStudentsWithHomework(2).size());
    }

    @Test
    public void testGetStudentsWithHomeworkToATeacher() {
        assertEquals(2, reportsService.getStudentsWithHomeworkToATeacher(2, "Alice").size());
    }

    @Test
    public void getGradesFormAHomeorkInAWeek() {
        assertEquals(1, reportsService.getGradesFromAHomeworkInAWeek(1, 7).size());
    }
}
