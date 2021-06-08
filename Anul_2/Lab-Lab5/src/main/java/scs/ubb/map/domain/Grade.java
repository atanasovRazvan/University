package scs.ubb.map.domain;

import java.time.LocalDate;

public class Grade extends Entity<String> {
    private String teacher;
    private LocalDate date;
    private float grade;
    private Long studentId;
    private Integer homeworkId;

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public Grade(String teacher, float grade, LocalDate date, Long studentId, Integer homeworkId) {
        this.teacher = teacher;
        this.grade = grade;
        this.date = date;
        this.studentId = studentId;
        this.homeworkId = homeworkId;
        super.setId(studentId + "_" + homeworkId);
    }

    public String getTeacher() {
        return teacher;
    }

    public LocalDate getDate() {
        return date;
    }

    public float getGrade() {
        return grade;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

}
