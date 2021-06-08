package scs.ubb.map.domain;

public class Homework extends Entity<Integer> {
    private String description;
    private int startWeek;
    private int deadlineWeek;

    public String getDescription() {
        return description;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public int getDeadlineWeek() {
        return deadlineWeek;
    }

    public Homework(String description, int startWeek, int deadlineWeek) {
        this.description = description;
        this.startWeek = startWeek;
        this.deadlineWeek = deadlineWeek;
    }
}
