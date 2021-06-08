package ubb.scs.map.ir.sem1.factory;

import ubb.scs.map.ir.sem1.containers.Container;
import ubb.scs.map.ir.sem1.containers.StackContainer;
import ubb.scs.map.ir.sem1.containers.Strategy;

public class TaskContainerFactory implements Factory {
    public static final Factory INSTANCE = new TaskContainerFactory();
    private static Factory instance = null;
    public static Factory getInstance(){
        if (instance == null){
            instance = new TaskContainerFactory();
        }
        return instance;
    }

    private TaskContainerFactory() {}

    @Override
    public Container createContainer(Strategy strategy) {
        switch (strategy){
            case LIFO:
                return new StackContainer();
            case FIFO:
                //return new QueueContainer();
                return null;
            default:
                return null;
        }
    }
}
