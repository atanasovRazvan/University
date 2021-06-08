package ubb.scs.map.ir.sem1.model;

import java.util.Objects;

public abstract class Task {
    private String tastkID;
    private String descriere;

    public Task(){

    }

    public Task(String tastkID, String descriere) {
        this.tastkID = tastkID;
        this.descriere = descriere;
    }

    public String getTastkID() {
        return tastkID;
    }

    public void setTastkID(String tastkID) {
        this.tastkID = tastkID;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public abstract void execute();

    @Override
    public String toString() {
        return tastkID + " " + descriere;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(getTastkID(), task.getTastkID()) &&
                Objects.equals(getDescriere(), task.getDescriere());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTastkID(), getDescriere());
    }
}
