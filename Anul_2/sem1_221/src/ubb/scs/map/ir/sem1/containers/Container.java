package ubb.scs.map.ir.sem1.containers;

import ubb.scs.map.ir.sem1.model.Task;

public interface Container {

    Task remove();
    void add(Task task);
    int size();
    boolean isEmpty();
}

