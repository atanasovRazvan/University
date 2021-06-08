package factory;

import containers.Container;

public interface Factory {

    public Container createContainer(Strategy strategy);

}
