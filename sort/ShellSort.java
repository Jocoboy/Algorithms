package sort;

import static sort.util.Utils.*;

/**
 * Optimize InsertionSort.
 * 
 * @see Sort
 */

public class ShellSort implements Sort {

    /**
     * Sort the array in increasing order.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int len = array.length;
        int gap = 1;
        while (gap < len / 3) {
            gap = 3 * gap + 1;
        }
        while (gap >= 1) {
            for (int i = gap; i < len; i++) {
                for (int j = i; j >= gap && less(array[j], array[j - gap]); j -= gap) {
                    swap(array, j, j - gap);
                }
            }
            gap /= 3;
        }
        return array;
    }

    /**
     * Drive program.
     * 
     * @param args
     */
    public static void main(String args[]) {
        ShellSort shellSort = new ShellSort();

        // Integer Input
        Integer[] integers = { 77, 5, 133, 197, 1586, -17, -111, 0, 19, 21 };
        shellSort.sort(integers);
        print(integers);

        // String Input
        String[] strings = { "g", "f", "z", "w", "A", "o", "P" };
        print(shellSort.sort(strings));
    }
}