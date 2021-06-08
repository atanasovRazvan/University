package Domain.Auxil;

import java.util.Objects;

public class FeedbackDTO {
    private String assignmentDescription, feedback;
    private float grade;
    private int submissionWeek, deadlineWeek;

    public FeedbackDTO(String assignmentDescription, float grade, int submissionWeek, int deadlineWeek, String feedback) {
        this.assignmentDescription = assignmentDescription;
        this.grade = grade;
        this.submissionWeek = submissionWeek;
        this.deadlineWeek = deadlineWeek;
        this.feedback = feedback;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackDTO that = (FeedbackDTO) o;
        return Float.compare(that.grade, grade) == 0 &&
                submissionWeek == that.submissionWeek &&
                deadlineWeek == that.deadlineWeek &&
                Objects.equals(assignmentDescription, that.assignmentDescription) &&
                Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignmentDescription, feedback, grade, submissionWeek, deadlineWeek);
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public int getSubmissionWeek() {
        return submissionWeek;
    }

    public void setSubmissionWeek(int submissionWeek) {
        this.submissionWeek = submissionWeek;
    }

    public int getDeadlineWeek() {
        return deadlineWeek;
    }

    public void setDeadlineWeek(int deadlineWeek) {
        this.deadlineWeek = deadlineWeek;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "assignmentDescription: " + assignmentDescription + '|' +
                "feedback: " + feedback + '|' +
                "grade: " + grade + '|' +
                "submissionWeek: " + submissionWeek + '|' +
                "deadlineWeek: " + deadlineWeek;
    }
}
