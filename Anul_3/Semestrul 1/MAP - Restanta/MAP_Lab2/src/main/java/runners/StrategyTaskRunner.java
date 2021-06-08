package runners;

import containers.Container;
import domain.Task;
import factory.Strategy;
import factory.TaskContainerFactory;

public class StrategyTaskRunner implements TaskRunner {

    private Container container;

    public StrategyTaskRunner(Strategy strategy) {
        this.container = TaskContainerFactory.getInstance().createContainer(strategy);
    }

    @Override
    public void executeOneTask() {
        if(!this.container.isEmpty())
            this.container.remove().execute();
    }

    @Override
    public void executeAll() {
        while(!this.container.isEmpty())
            this.container.remove().execute();
    }

    @Override
    public void addTask(Task t) {
        this.container.add(t);
    }

    @Override
    public boolean hasTask() {
        return !container.isEmpty();
    }
}
