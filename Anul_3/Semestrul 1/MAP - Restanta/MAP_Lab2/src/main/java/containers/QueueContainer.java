package containers;

import domain.Task;

public class QueueContainer extends AbstractContainer {

    public QueueContainer() {
        super();
    }

    @Override
    public Task remove() {
        Task taskRemoved = getTasks()[0];

        Task[] tasks = getTasks();
        for(int i = 0; i < getSize(); i ++)
            tasks[i] = tasks[i+1];
        setTasks(tasks);
        setSize(getSize()-1);

        return taskRemoved;
    }

}
