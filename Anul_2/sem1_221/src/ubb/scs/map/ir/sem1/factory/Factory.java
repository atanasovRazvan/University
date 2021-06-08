package ubb.scs.map.ir.sem1.factory;

import ubb.scs.map.ir.sem1.containers.Container;
import ubb.scs.map.ir.sem1.containers.Strategy;

public interface Factory {
    Container createContainer(Strategy startegy);
}

