package ubb.scs.map.ir.sem1.utils;

import ubb.scs.map.ir.sem1.model.AbstractSorter;

public class BubbleSort extends AbstractSorter {
    public BubbleSort(int[] vector) {
        super(vector);
    }

    @Override
    public int[] sort() {
        return  sortare(super.vector);
    }

    public int[] sortare(int[] v) {
        int n = v.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (v[j - 1] > v[j]) {
                    temp = v[j - 1];
                    v[j - 1] = v[j];
                    v[j] = temp;
                }
            }
        }
        return v;
    }
}
