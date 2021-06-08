package ubb.scs.map.ir.sem1.containers;

import ubb.scs.map.ir.sem1.model.Task;
import ubb.scs.map.ir.sem1.utils.ArrayExceptions;

public class StackContainer extends AbstractContainer {
    public StackContainer() {}

    @Override
    public Task remove() {
        Task aux = null;
        try{
            aux = super.tasks.delete(super.tasks.size() - 1);
        } catch (ArrayExceptions arrayExceptions) {
            arrayExceptions.getMesaj();
        }
        return aux;
    }
}
