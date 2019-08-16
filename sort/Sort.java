package sort;

import java.util.Arrays;
import java.util.List;

/**
 * Common interface for most sorting algorithms.
 */
public interface Sort {

    /**
     * 
     * @param <T>
     * @param unsorted - an array to be sorted
     * @return a sorted array
     */
    <T extends Comparable<T>> T[] sort(T[] unsorted);

    /**
     * 
     * @param <T>
     * @param unsorted - an list to be sorted
     * @return a sorted list
     */
    @SuppressWarnings("unchecked")
    default <T extends Comparable<T>> List<T> sort(List<T> unsorted) {
        return Arrays.asList(sort(unsorted.toArray((T[]) new Comparable[unsorted.size()])));
    }
}