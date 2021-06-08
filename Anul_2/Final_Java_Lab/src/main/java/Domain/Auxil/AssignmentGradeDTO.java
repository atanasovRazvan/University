package Domain.Auxil;

import java.time.LocalDate;

public class AssignmentGradeDTO {
    private String assignmentName;
    private float grade;
    private LocalDate date;

    public AssignmentGradeDTO(String assignmentName, float grade, LocalDate date) {
        this.assignmentName = assignmentName;
        this.grade = grade;
        this.date = date;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
