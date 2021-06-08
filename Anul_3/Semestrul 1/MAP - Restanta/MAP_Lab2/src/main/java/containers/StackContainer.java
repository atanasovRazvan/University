package containers;

import domain.Task;

public class StackContainer extends AbstractContainer {

    public StackContainer() {
        super();
    }

    @Override
    public Task remove() {
        setSize(getSize()-1);
        return getTasks()[getSize()];
    }

}