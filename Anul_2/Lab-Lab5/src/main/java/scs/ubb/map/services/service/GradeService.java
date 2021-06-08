package scs.ubb.map.services.service;

import scs.ubb.map.domain.Grade;
import scs.ubb.map.domain.GradeDTO;
import scs.ubb.map.domain.Homework;
import scs.ubb.map.domain.Student;
import scs.ubb.map.repository.CrudRepository;
import scs.ubb.map.repository.files.json.JSONRepository;
import scs.ubb.map.utils.AcademicYear;

public class GradeService extends Service<String, Grade> {
    private JSONRepository<GradeDTO> gradeDTOJSONRepository;
    public GradeService(CrudRepository<String, Grade> repository, JSONRepository<GradeDTO> gradeDTOJSONRepository) {
        super(repository);
        this.gradeDTOJSONRepository = gradeDTOJSONRepository;
    }

    public Grade save(Grade grade, StudentService studentService, HomeworkService homeworkService, String feedback) {
        Grade exitGrade = super.save(grade);
        if(exitGrade != null) {
            return exitGrade;
        }

        Student student = studentService.findOne(grade.getStudentId());
        Homework homework = homeworkService.findOne(grade.getHomeworkId());
        String studentName = student.getFirstName() + " " + student.getLastName();

        GradeDTO gradeDTO = new GradeDTO(studentName, homework.getId(), grade.getGrade(),
                AcademicYear.getInstance().getSemesterWeek(grade.getDate()), homework.getDeadlineWeek(), feedback);
        this.gradeDTOJSONRepository.write(gradeDTO);
        return exitGrade;
    }
}
