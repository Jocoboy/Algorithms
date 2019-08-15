package sort.util;

import java.util.Arrays;

/**
 * Utility methods for driving program.
 */
public class Utils {

    /**
     * 
     * @param <T>
     * @param u   - first element
     * @param v   - second element
     * @return true if the first element is less than the second
     */
    public static <T extends Comparable<T>> boolean less(T u, T v) {
        return u.compareTo(v) < 0;
    }

     /**
     * 
     * @param <T>
     * @param u   - first element
     * @param v   - second element
     * @return true if the first element is greater than the second
     */
    public static <T extends Comparable<T>> boolean greater(T u, T v) {
        return u.compareTo(v) > 0;
    }

    /**
     * 
     * @param <T>
     * @param array - target array to be sorted
     * @param idx   - index of first element
     * @param idy   - index of second element
     * @return true if swap successfully
     */
    public static <T> boolean swap(T[] array, int idx, int idy) {
        T temp = array[idx];
        array[idx] = array[idy];
        array[idy] = temp;
        return true;
    }

    public static void print(Object[] obj) {
        System.out.println(Arrays.toString(obj));
    }
}