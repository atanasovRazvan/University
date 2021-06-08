package ubb.scs.map.ir.sem1.containers;

import ubb.scs.map.ir.sem1.model.Task;
import ubb.scs.map.ir.sem1.runner.AbstractTaskRunner;
import ubb.scs.map.ir.sem1.utils.TaskArray;

public abstract class AbstractContainer implements Container {
    protected TaskArray tasks = new TaskArray();

    public AbstractContainer() {}

    @Override
    public abstract Task remove();

    @Override
    public void add(Task task) {
        tasks.push_back(task);
    }

    @Override
    public int size() {
        return tasks.size();
    }

    @Override
    public boolean isEmpty() {
        return tasks.size() == 0;
    }
}
