package Utils;

import java.util.Objects;

/**
 * Pair utility class
 * @param <T> - type of the first item of the pair
 * @param <E> - type of the second item of the pair
 */
public class Pair<T, E> {
    private T first;
    private E second;

    public Pair(T first, E second) {
        this.first = first;
        this.second = second;
    }

    /**
     * returns the first element of the pair
     * @return first - T
     */
    public T getFirst() {
        return first;
    }

    /**
     * sets the first element of the pair
     * @param first - T
     */
    public void setFirst(T first) {
        this.first = first;
    }

    /**
     * returns the second element of the pair
     * @return second - E
     */
    public E getSecond() {
        return second;
    }

    /**
     * sets the second element of the pair
     * @param second - E
     */
    public void setSecond(E second) {
        this.second = second;
    }

    /**
     * returns if two pairs are equal or not
     * @param o - other pair
     * @return true if pairs are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }

    /**
     * returns the hashcode of the pair
     * @return hashcode - int
     */
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    /**
     * returns the string representation of the pair
     * @return stringRepresentation - String
     */
    @Override
    public String toString() {
        return  "first: " + first + '|' +
                "second: " + second;
    }
}

