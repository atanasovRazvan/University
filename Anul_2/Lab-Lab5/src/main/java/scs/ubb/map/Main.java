package scs.ubb.map;

import scs.ubb.map.domain.Grade;
import scs.ubb.map.handlers.GradeHandler;
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
import scs.ubb.map.validators.repository.GradeValidator;
import scs.ubb.map.validators.repository.HomeworkValidator;
import scs.ubb.map.validators.repository.StudentValidator;

import java.time.LocalDate;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        AcademicYear academicYear = new AcademicYear(Config.getProperties().getProperty("year-data"));
        CrudRepository studentRepo = new StudentFileRepository(new StudentValidator(),
                Config.getProperties().getProperty("student-data"));
        CrudRepository homeworkRepo = new HomeworkFileRepository(new HomeworkValidator(),
                Config.getProperties().getProperty("homework-data"));
        CrudRepository gradeRepo = new GradeFileRepository(new GradeValidator(studentRepo, homeworkRepo),
                Config.getProperties().getProperty("grade-data"));

        JSONRepository jsonRepository = new GradeJSONRepository("data/studentsGrades/");

        Service studentService = new StudentService(studentRepo);
        Service homeworkService = new HomeworkService(homeworkRepo);
        Service gradeService = new GradeService(gradeRepo, jsonRepository);

        GradeHandler gradeHandler = new GradeHandler((HomeworkService) homeworkService);
        Grade grade = new Grade("Nicu", 10, LocalDate.now(), 2l, 4);
        grade.setId("1_2");
        grade = gradeHandler.getGradeWithConstraints(grade, new HashMap<>());
        ((GradeService) gradeService).save(grade, (StudentService) studentService, (HomeworkService) homeworkService,
                "Prea bun");
    }
}
