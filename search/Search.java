package search;

/**
 * Common interface for most searching algorithms.
 */
public interface Search {
    /**
     * 
     * @param <T> - any comparable type
     * @param array - an array where the element should be found
     * @param value - an element to be found
     * @return first found index of the element
     */
    <T extends Comparable<T>> int find(T[] array, T value);
}