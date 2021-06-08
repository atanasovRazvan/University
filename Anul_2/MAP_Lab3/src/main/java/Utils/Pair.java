package Utils;

public class Pair<T, U> {

    public T first;
    public U second;

    public Pair(T Object1, U Object2){
        this.first = Object1;
        this.second = Object2;
    }

    /**
     * returns the first object in the Pair
     * @return first - Object
     */
    public T first(){
        return this.first;
    }

    /**
     * returns the second object in the Pair
     * @return second - Object
     */
    public U second(){
        return this.second;
    }

}
