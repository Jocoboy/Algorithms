package sort;

import static sort.util.Utils.*;

/**
 * @see Sort
 */

public class MergeSort implements Sort {

    /**
     * Recursively sort the array in increasing order.
     * 
     * @param <T>
     * @param array - the array to be sorted
     * @param temp  - the copy of the actual array
     * @param left  - the first index of the array
     * @param right - the last index of the array
     */
    private static <T extends Comparable<T>> void doSort(T[] array, T[] temp, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            doSort(array, temp, left, middle);
            doSort(array, temp, middle + 1, left);
            merge(array, temp, left, middle, right);
        }
    }

    /**
     * 
     * @param <T>
     * @param array  - the array to be sorted
     * @param temp   - the copy of the actual array
     * @param left   - the first index of the array
     * @param middle - the middle index of the array
     * @param right  - the last index of the array
     */
    private static <T extends Comparable<T>> void merge(T[] array, T[] temp, int left, int middle, int right) {
        System.arraycopy(array, left, temp, left, right - left + 1);

        int l = left;
        int r = middle + 1;
        int st = left;

        while (l <= middle && r <= right) {
            if (less(temp[l], temp[r])) {
                array[st++] = temp[l++];
            } else {
                array[st++] = temp[r++];
            }
        }

        while (l <= middle) {
            array[st++] = temp[l++];
        }
        while (r <= right) {
            array[st++] = temp[r++];
        }
    }

    /**
     * Sort the array in increasing order.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        T[] temp = (T[]) new Comparable[unsorted.length];
        doSort(unsorted, temp, 0, unsorted.length - 1);
        return unsorted;
    }

    /**
     * Drive program.
     * 
     * @param args
     */
    public static void main(String args[]) {
        MergeSort mergeSort = new MergeSort();

        // Integer Input
        Integer[] integers = { 77, 5, 133, 197, 1586, -17, -111, 0, 19, 21 };
        mergeSort.sort(integers);
        print(integers);

        // String Input
        String[] strings = { "g", "f", "z", "w", "A", "o", "P" };
        print(mergeSort.sort(strings));
    }
}