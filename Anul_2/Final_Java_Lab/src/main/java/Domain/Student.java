package Domain;

import java.util.Objects;

/**
 * An university student
 */
public class Student extends Entity<String> {
    private String firstName;
    private String lastName;
    private int group;
    private String email;
    private String coordinator;

    public Student(String id, String firstName, String lastName, int group, String email, String coordinator) {
        this.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.email = email;
        this.coordinator = coordinator;
    }

    /**
     * returns student's first group
     *
     * @return group - int
     */
    public int getGroup() {
        return group;
    }

    /**
     * sets student's first group
     *
     * @param group - int
     */
    public void setGroup(int group) {
        this.group = group;
    }

    /**
     * returns student's first name
     *
     * @return firstName - String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets student's first name
     *
     * @param firstName - String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * returns student's last name
     *
     * @return lastName - String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets student's last name
     *
     * @param lastName - String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * returns if two students are equal
     *
     * @param o - Student, the other student
     * @return true if students are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return group == student.group &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(email, student.email) &&
                Objects.equals(coordinator, student.coordinator);
    }


    /**
     * returns student's hashcode
     *
     * @return hashcode - int
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, group, email, coordinator);
    }

    /**
     * returns student's email
     *
     * @return email - String
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the student's email
     *
     * @param email - String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * returns the student's coordinator
     *
     * @return coordinator - String
     */
    public String getCoordinator() {
        return coordinator;
    }

    /**
     * sets the student's coordinator
     *
     * @param coordinator - String
     */
    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    /**
     * returns the string representation of a student
     *
     * @return student representation - String
     */
    @Override
    public String toString() {
        return "id: " + getId() + '|' +
                "firstName: " + firstName + '|' +
                "lastName: " + lastName + '|' +
                "group: " + group + '|' +
                "email: " + email + '|' +
                "coordinator: " + coordinator;
    }

    /**
     * returns the file string representation of a student
     *
     * @return student representation - String
     */
    @Override
    public String toFileString() {
        return getId() + '/' +
                firstName + '/' +
                lastName + '/' +
                group + '/' +
                email + '/' +
                coordinator;
    }
}