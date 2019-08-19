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
    default <T extends Comparable<T>> int find(T[] array, T value){
        return 0;
    }

    /**
     * 
     * @param <T> - any comparable type
     * @param array - a 2D array where the element should be found
     * @param value - an element to be found
     * @return first found location of the element
     */
    default <T extends Comparable<T>> int[] find(T[][] array, T value){
        return new int[2];
    }
}