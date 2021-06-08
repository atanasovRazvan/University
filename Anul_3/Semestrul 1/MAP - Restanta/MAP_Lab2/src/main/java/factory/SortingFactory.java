package factory;

import domain.Task;
import sorters.SortingStrategy;

public interface SortingFactory {

    public Task createSorter(String taskId, String desc, Integer[] array, SortingStrategy strategy);

}
