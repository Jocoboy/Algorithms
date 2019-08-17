package sort;

import static sort.util.Utils.*;

/**
 * @see Sort
 */

public class InsertionSort implements Sort {

    /**
     * Sort the array in increasing order.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T selection = array[i];
            int j = i - 1;
            while (j >= 0 && less(selection, array[j])) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = selection;
        }
        return array;
    }

    /**
     * Drive program.
     * 
     * @param args
     */
    public static void main(String args[]) {
        InsertionSort insertionSort = new InsertionSort();

        // Integer Input
        Integer[] integers = { 77, 5, 133, 197, 1586, -17, -111, 0, 19, 21 };
        insertionSort.sort(integers);
        print(integers);

        // String Input
        String[] strings = { "g", "f", "z", "w", "A", "o", "P" };
        print(insertionSort.sort(strings));
    }
}