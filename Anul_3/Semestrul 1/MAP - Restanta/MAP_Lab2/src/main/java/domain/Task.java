package domain;
import java.util.Objects;

public abstract class Task {
    private String taskId;
    private String desc;

    public Task(String taskId, String desc) {
        this.taskId = taskId;
        this.desc = desc;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public abstract void execute();

    @Override
    public String toString() {
        return taskId + " " + desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getTaskId().equals(task.getTaskId()) &&
                desc.equals(task.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskId(), desc);
    }
}
