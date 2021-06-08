package Domain;

import com.silviucanton.services.config.ApplicationContext;

import java.util.Objects;

/**
 * An assignment at a discipline
 */
public class Assignment extends Entity<Integer> {
    private String description;
    private int startWeek;
    private int deadlineWeek;

    public Assignment(int id, String description, int deadlineWeek) {
        this.setId(id);
        this.description = description;
        this.startWeek = ApplicationContext.getYearStructure().getCurrentWeek(ApplicationContext.getCurrentLocalDate());
        this.deadlineWeek = deadlineWeek;
    }

    /**
     * returns the assignment description
     *
     * @return description - String
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the assignment description
     *
     * @param description - String containing the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * returns the start week of the assignment
     *
     * @return startWeek - int
     */
    public int getStartWeek() {
        return startWeek;
    }

    /**
     * sets the start week of the assignment
     *
     * @param startWeek - int, the new deadlineWeek
     */
    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    /**
     * returns the deadline week of the assignment
     *
     * @return deadlineWeek - int
     */
    public int getDeadlineWeek() {
        return deadlineWeek;
    }

    /**
     * sets the deadline week of the assignment
     *
     * @param deadlineWeek - int, the new deadlineWeek
     */
    public void setDeadlineWeek(int deadlineWeek) {
        this.deadlineWeek = deadlineWeek;
    }

    /**
     * retruns if two assignments are equal
     *
     * @param o - the other assignment
     * @return true if assignments are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment assignment = (Assignment) o;
        return startWeek == assignment.startWeek &&
                deadlineWeek == assignment.deadlineWeek &&
                Objects.equals(description, assignment.description);
    }

    /**
     * returns the hashcode of a assignment
     *
     * @return hashCode - int
     */
    @Override
    public int hashCode() {
        return Objects.hash(description, startWeek, deadlineWeek);
    }

    /**
     * returns the String representation of a assignment
     *
     * @return assignment representation - String
     */
    @Override
    public String toString() {
        return "id: " + getId() + '|' +
                "description: " + description + '|' +
                "startWeek: " + startWeek + '|' +
                "deadlineWeek: " + deadlineWeek;
    }

    /**
     * returns the file String representation of a assignment
     *
     * @return assignment representation - String
     */
    @Override
    public String toFileString() {
        return getId() + "/" +
                description + '/' +
                startWeek + '/' +
                deadlineWeek;
    }
}