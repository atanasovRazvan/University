package sorters;

import domain.Task;

import java.util.Arrays;

public abstract class SorterTask extends Task {

    private Integer[] array;

    public SorterTask(String taskId, String desc, Integer[] array) {
        super(taskId, desc);
        this.array = array;
    }

    public Integer[] getArray() {
        return array;
    }

    public abstract Integer[] sort();

    @Override
    public void execute() {
        System.out.println(this.toString() + "\n" + Arrays.toString(sort()));
    }

}
