package Domain.Auxil;

public class StudentGradeDTO {
    private String studentName;
    private float finalGrade;

    public StudentGradeDTO(String studentName, float finalGrade) {
        this.studentName = studentName;
        this.finalGrade = finalGrade;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public float getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(float finalGrade) {
        this.finalGrade = finalGrade;
    }
}
