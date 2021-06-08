package factory;

import domain.Task;
import sorters.BubbleSortTask;
import sorters.QuickSortTask;
import sorters.SortingStrategy;

public class SortingTaskFactory implements SortingFactory {

    private static SortingTaskFactory instance = null;

    private SortingTaskFactory() {}

    public static SortingTaskFactory getInstance(){
        if(instance == null)
            instance = new SortingTaskFactory();
        return instance;
    }

    @Override
    public Task createSorter(String taskId, String desc, Integer[] array, SortingStrategy strategy){
        if(strategy == SortingStrategy.BUBBLESORT)
            return new BubbleSortTask(taskId, desc, array);
        else
            return new QuickSortTask(taskId, desc, array);
    }
}
