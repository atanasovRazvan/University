package ubb.scs.map.ir.sem1.containers;

import ubb.scs.map.ir.sem1.model.Task;
import ubb.scs.map.ir.sem1.utils.ArrayExceptions;

public class QueueContainer extends AbstractContainer {
    public QueueContainer() {}

    @Override
    public Task remove() {
        Task aux = null;
        try {
            aux = super.tasks.delete(0);
            for (int i=1;i<=super.size();i++)
                super.tasks.push_back_byIndex(super.tasks.delete(i), i-1);
        } catch (ArrayExceptions arrayExceptions) {
            arrayExceptions.getMesaj();
        }
        return aux;
    }
}
