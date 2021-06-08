package ubb.scs.map.ir.sem1.utils;

import ubb.scs.map.ir.sem1.model.AbstractSorter;

public class QuickSort extends AbstractSorter {
    public QuickSort(int[] vector) {
        super(vector);
    }

    @Override
    public int[] sort() {
        return sortare(super.vector);
    }

    public int[] sortare(int[] v) {
        int left = 0;
        int right = v.length - 1;
        quickSort(v, left, right);
        return v;
    }

    private void quickSort(int v[], int left, int right) {
        if (left >= right)
            return;
        int pivot = v[right];
        int partition = partition(v, left, right, pivot);
        quickSort(v, 0, partition - 1);
        quickSort(v, partition + 1, right);
    }

    private int partition(int[] v, int left, int right, int pivot) {
        int leftCursor = left-1;
        int rightCursor = right;
        while(leftCursor<rightCursor){
            while(v[++leftCursor]<pivot);
            while(rightCursor > 0 && v[--rightCursor] > pivot);
            if(leftCursor >= rightCursor){
                break;
            }else {
                swap(v,leftCursor, rightCursor);
            }
        }
        swap(v,leftCursor,right);
        return leftCursor;
    }

    private void swap(int[] v, int left, int right) {
        int temp=v[left];
        v[left]=v[right];
        v[right]=temp;
    }
}
