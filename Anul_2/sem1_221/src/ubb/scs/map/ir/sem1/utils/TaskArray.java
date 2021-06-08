package ubb.scs.map.ir.sem1.utils;

import ubb.scs.map.ir.sem1.model.Task;

public class TaskArray {
    private Task tasks[] = new Task[20];
    private int size = 0;

    public TaskArray() {}

    private void resize(){
        Task tasksAux[] = new Task[(tasks.length*2)];
        System.arraycopy(tasks, 0, tasksAux,0, size);
        tasks = tasksAux;
    }

    public Task get(int index){
        return tasks[index];
    }

    public void push_back(Task elem){
        if (size == tasks.length)
            resize();
        tasks[size] = elem;
        size++;
    }

    public void push_back_byIndex(Task elem, int i){
        if (size ==  tasks.length)
            resize();
        if (get(i) == null){
            tasks[i] = elem;
            size++;
        }
    }

    public Task delete(int index) throws ArrayExceptions{
        if (size == 0)
            throw new ArrayExceptions("Vectorul este gol!");
        else {
            if (get(index) == null)
                throw new ArrayExceptions("Nu exista element pe pozitia: " + index);
            else {
                size--;
                Task aux = tasks[index];
                tasks[index] = null;
                return aux;
            }

        }
    }

    public int size(){
        return size;
    }
}
