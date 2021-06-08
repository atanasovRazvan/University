package runners;

import domain.Task;

public abstract class AbstractTaskRunner implements TaskRunner {

    private TaskRunner taskRunner;

    public AbstractTaskRunner(TaskRunner taskRunner) {
        this.taskRunner = taskRunner;
    }

    @Override
    public void executeOneTask() {
        taskRunner.executeOneTask();
        afterExecution();
    }

    public abstract void afterExecution();

    @Override
    public void executeAll() {
        while(taskRunner.hasTask())
            executeOneTask();
    }

    @Override
    public void addTask(Task t) {
        taskRunner.addTask(t);
    }

    @Override
    public boolean hasTask() {
        return taskRunner.hasTask();
    }
}
