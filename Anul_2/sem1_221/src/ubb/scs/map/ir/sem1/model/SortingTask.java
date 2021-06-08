package ubb.scs.map.ir.sem1.model;

import ubb.scs.map.ir.sem1.utils.BubbleSort;
import ubb.scs.map.ir.sem1.utils.QuickSort;

public class SortingTask extends Task {
    private int[] vector;
    private SortingAlgorithms algorithm;
    private AbstractSorter sorter;

    public SortingTask(String tastkID, String descriere, int[] vector, SortingAlgorithms algorithm) {
        super(tastkID, descriere);
        this.vector =  vector;
        this.algorithm = algorithm;
    }

   @Override
    public void execute() {
        if (algorithm.equals(SortingAlgorithms.BubbleSort)) {
            sorter = new BubbleSort(vector);
            System.out.println("BubbleSort");
        }
        else {
            sorter = new QuickSort(vector);
            System.out.println("QuickSort");
        }
        for (int i:sorter.sort()) {
            System.out.println(i+"");
        }
    }
}
