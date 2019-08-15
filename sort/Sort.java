package sort;

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
}