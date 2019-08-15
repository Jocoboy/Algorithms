package sort;

import java.util.Random;
import static sort.util.Utils.*;

/**
 * @see Sort
 */
public class BogoSort implements Sort {

    /**
     * 
     * @param <T>
     * @param array - current array
     * @return true if the array is correctly sorted
     */
    private static <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (greater(array[i], array[i + 1]))
                return false;
        }
        return true;
    }

    /**
     * 
     * @param <T>
     * @param array - array to be shuffled randomly
     */
    private static <T> void nextPermutation(T[] array) {
        Random random = new Random();
        int len = array.length;
        for (int i = 0; i < len; i++) {
            int randomIndex = i + random.nextInt(len - i);
            swap(array, randomIndex, i);
        }
    }

    /**
     * Sort the array in increasing order.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T array[]) {
        while (!isSorted(array)) {
            nextPermutation(array);
        }
        return array;
    }

    /**
     * Drive program.
     * 
     * @param args
     */
    public static void main(String args[]) {
        BogoSort bogoSort = new BogoSort();

        // Integer Input
        Integer[] integers = { 77, 5, 133, 197, 1586, -17, -111, 0, 19, 21 };
        bogoSort.sort(integers);
        print(integers);

        // String Input
        String[] strings = { "g", "f", "z", "w", "A", "o", "P" };
        print(bogoSort.sort(strings));
    }
}