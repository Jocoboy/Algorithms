package sort;

import static sort.util.Utils.*;

/**
 * @see Sort
 */
public class BubbleSort implements Sort {

    /**
     * Sort the array in increasing order.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int len = array.length;
        boolean canSwap;
        do {
            canSwap = false;
            for (int i = 0; i < len - 1; i++) {
                if (greater(array[i], array[i + 1])) {
                    canSwap = swap(array, i, i + 1);
                }
            }
            len--;
        } while (canSwap);
        return array;
    }

    /**
     * Drive program.
     * 
     * @param args
     */
    public static void main(String args[]) {
        BubbleSort bubbleSort = new BubbleSort();

        // Integer Input
        Integer[] integers = { 77, 5, 133, 197, 1586, -17, -111, 0, 19, 21 };
        bubbleSort.sort(integers);
        print(integers);

        // String Input
        String[] strings = { "g", "f", "z", "w", "A", "o", "P" };
        print(bubbleSort.sort(strings));
    }
}