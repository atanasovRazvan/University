package socialnetwork.domain;

import java.time.LocalDateTime;

public class Friend {

    public String firstName, lastName;
    public LocalDateTime dateTime;

    public Friend(String firstName, String lastName, LocalDateTime dateTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateTime = dateTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return lastName + " | " + firstName + " | " + dateTime.toString();
    }
}
