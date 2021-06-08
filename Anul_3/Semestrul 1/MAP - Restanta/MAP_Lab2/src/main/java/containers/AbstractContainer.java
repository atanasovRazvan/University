package containers;

import domain.Task;

public abstract class AbstractContainer implements Container {

    private Task[] tasks;
    private int size;

    public AbstractContainer(){
        this.tasks = new Task[10];
        this.size = 0;
    }

    @Override
    public abstract Task remove();

    @Override
    public void add(Task task) {
        if (tasks.length==size)
        {
            Task t[]=new Task[tasks.length*2];
            System.arraycopy( tasks, 0, t, 0, tasks.length );
            tasks=t;
        }
        tasks[size]=task;
        size++;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }
}
