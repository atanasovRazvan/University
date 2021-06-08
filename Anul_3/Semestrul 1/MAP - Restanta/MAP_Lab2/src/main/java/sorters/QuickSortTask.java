package sorters;

public class QuickSortTask extends SorterTask {


    public QuickSortTask(String taskId, String desc, Integer[] array) {
        super(taskId, desc, array);
    }

    @Override
    public Integer[] sort() {
        Integer[] sortedArray = this.getArray();
        quick_sort(sortedArray, 0, sortedArray.length-1);
        return sortedArray;
    }

    int partition(Integer intArray[], int low, int high) {
        int pi = intArray[high];
        int i = (low-1); // smaller element index
        for (int j=low; j<high; j++) {
            // check if current element is less than or equal to pi
            if (intArray[j] <= pi) {
                i++;
                // swap intArray[i] and intArray[j]
                int temp = intArray[i];
                intArray[i] = intArray[j];
                intArray[j] = temp;
            }
        }

        // swap intArray[i+1] and intArray[high] (or pi)
        int temp = intArray[i+1];
        intArray[i+1] = intArray[high];
        intArray[high] = temp;

        return i+1;
    }

    void quick_sort(Integer intArray[], int low, int high) {
        if (low < high) {
            //partition the array around pi=>partitioning index and return pi
            int pi = partition(intArray, low, high);

            // sort each partition recursively
            quick_sort(intArray, low, pi-1);
            quick_sort(intArray, pi+1, high);
        }
    }

}
