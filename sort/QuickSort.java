package sort;

import static sort.util.Utils.*;

/**
 * Optimize BubbleSort.
 * 
 * @see Sort
 */
public class QuickSort implements Sort {

    /**
     * 
     * @param <T>
     * @param array - the array to be sorted
     * @param left  - the first index of the array
     * @param right - the last index of the array
     */
    private static <T extends Comparable<T>> void doSort(T[] array, int left, int right) {
        if (left < right) {
            int pivot = randomPartition(array, left, right);
            doSort(array, left, pivot - 1);
            doSort(array, pivot, right);
        }
    }

    /**
     * Find the partition index of the array.
     *      Ramdomize the array to avoid the basically ordered sequences.
     * 
     * @param <T>
     * @param array - the array to be sorted
     * @param left  - the first index of the array
     * @param right - the last index of the array
     * @return the partition index of the array
     */
    private static <T extends Comparable<T>> int randomPartition(T[] array, int left, int right) {
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        swap(array, randomIndex, right);
        return partition(array, left, right);
    }

    /**
     * Find the partition index of the array.
     * 
     * @param <T>
     * @param array - the array to be sorted
     * @param left  - the first index of the array
     * @param right - the last index of the array
     * @return the partition index of the array
     */
    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        int middle = (left + right) / 2;
        while (left <= right) {
            // Find the possible index to be swapped in left part.
            while (less(array[left], array[middle])) {
                ++left;
            }
            // Find the possible index to be swapped in right part.
            while (less(array[middle], array[right])) {
                --right;
            }
            /**
             * If the left index less than middle and the right index greater than middle,
             * swap them and continue searching.
             */
            if (left <= right) {
                swap(array, left, right);
                ++left;
                --right;
            }
        }
        return left;
    }

    /**
     * Sort the array in increasing order.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        doSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * Drive program.
     * 
     * @param args
     */
    public static void main(String args[]) {
        QuickSort quickSort = new QuickSort();

        // Integer Input
        Integer[] integers = { 77, 5, 133, 197, 1586, -17, -111, 0, 19, 21 };
        quickSort.sort(integers);
        print(integers);

        // String Input
        String[] strings = { "g", "f", "z", "w", "A", "o", "P" };
        print(quickSort.sort(strings));
    }
}