package scs.ubb.map.domain;

import java.util.Objects;

public class Student extends Entity<Long> {
    private String lastName;
    private String firstName;
    private String email;
    private int group;

    public Student(String lastName, String firstName, String email, int group) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.group = group;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return group == student.group &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, email, group);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Student{" + getId().toString() +
                " name='" + lastName + " " + firstName + '\'' +
                ", group=" + group +
                '}';
    }
}
