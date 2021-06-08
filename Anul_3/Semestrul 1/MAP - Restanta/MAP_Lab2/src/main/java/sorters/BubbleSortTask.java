package sorters;

public class BubbleSortTask extends SorterTask {


    public BubbleSortTask(String taskId, String desc, Integer[] array) {
        super(taskId, desc, array);
    }

    @Override
    public Integer[] sort() {
        Integer[] sortedArray = this.getArray();
        int w;
        do {
            w = 0;
            for (int i = 1; i < sortedArray.length; i++)
                if (sortedArray[i - 1] > sortedArray[i]) {
                    Integer temp = sortedArray[i-1];
                    sortedArray[i-1] = sortedArray[i];
                    sortedArray[i] = temp;
                    w = 1;
                }
        }
        while(w == 1);
        return sortedArray;
    }
}
