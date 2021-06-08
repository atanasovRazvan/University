package scs.ubb.map.utils;

import scs.ubb.map.domain.Grade;
import scs.ubb.map.domain.GradeFilterDTO;
import scs.ubb.map.domain.Homework;
import scs.ubb.map.domain.Student;
import scs.ubb.map.services.service.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ReportsService {
    private Service<Long, Student> studentService;
    private Service<Integer, Homework> homeworkService;
    private Service<String, Grade> gradeService;

    public ReportsService(Service<Long, Student> studentService,
                          Service<Integer, Homework> homeworkService,
                          Service<String, Grade> gradeService) {
        this.studentService = studentService;
        this.homeworkService = homeworkService;
        this.gradeService = gradeService;
    }

    public List<Student> getStudentFromGroup(int targetGroup) {
        return StreamSupport.stream(studentService.findAll().spliterator(), false)
                .filter(student -> student.getGroup() == targetGroup)
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsWithHomework(int homeworkId) {
        return StreamSupport.stream(gradeService.findAll().spliterator(), false)
                .filter(grade -> grade.getHomeworkId() == homeworkId)
                .map(grade -> studentService.findOne(grade.getStudentId()))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsWithHomeworkToATeacher(int homeworkId, String teacherName) {
        return StreamSupport.stream(gradeService.findAll().spliterator(), false)
                .filter(grade -> grade.getHomeworkId() == homeworkId && grade.getTeacher().equals(teacherName))
                .map(grade -> studentService.findOne(grade.getStudentId()))
                .collect(Collectors.toList());
    }

    public List<GradeFilterDTO> getGradesFromAHomeworkInAWeek(int homeworkId, int week) {
        return StreamSupport.stream(gradeService.findAll().spliterator(), false)
                .filter(grade -> grade.getHomeworkId() == homeworkId &&
                        AcademicYear.getInstance().getSemesterWeek(grade.getDate()) == week)
                .map(grade -> {
                    Student student = studentService.findOne(grade.getStudentId());
                    return new GradeFilterDTO(student.getFirstName(),
                            student.getLastName(),
                            grade.getGrade(),
                            grade.getDate(),
                            grade.getHomeworkId(),
                            AcademicYear.getInstance().getSemesterWeek(grade.getDate()));
                })
                .collect(Collectors.toList());
    }
}
