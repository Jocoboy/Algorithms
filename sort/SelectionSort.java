package sort;

import static sort.util.Utils.*;

/**
 * @see Sort
 */
public class SelectionSort implements Sort {

    /**
     * Sort the array in increasing order.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (less(array[j], array[minIndex])) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(array, i, minIndex);
            }
        }
        return array;
    }

    /**
     * Drive program.
     * 
     * @param args
     */
    public static void main(String args[]) {
        SelectionSort selectionSort = new SelectionSort();

        // Integer Input
        Integer[] integers = { 77, 5, 133, 197, 1586, -17, -111, 0, 19, 21 };
        selectionSort.sort(integers);
        print(integers);

        // String Input
        String[] strings = { "g", "f", "z", "w", "A", "o", "P" };
        print(selectionSort.sort(strings));
    }
}