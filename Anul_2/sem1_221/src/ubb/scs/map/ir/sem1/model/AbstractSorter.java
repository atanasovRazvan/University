package ubb.scs.map.ir.sem1.model;

public abstract class AbstractSorter {
    protected int[] vector;

    public AbstractSorter(int[] vector) {
        this.vector = vector;
    }

    public abstract int[] sort();
}