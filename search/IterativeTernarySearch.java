package search;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 * This class represents iterative version {@link TernarySearch}.
 * This is a better way to implement the ternary search, because a recursive version adds some overhead to a stack.
 * But in java the compile can transform the recursive version to iterative implicitly,
 * so there are no much differences between these two algorithms.
 * <p>
 * Worst-case performance	O(log n)
 * Best-case performance	O(1)
 * Average performance	O(log n)
 * Worst-case space complexity	O(1)
 * 
 * @see Search
 * @see TernarySearch
 */
public class IterativeTernarySearch implements Search {

    @Override
    public <T extends Comparable<T>> int find(T[] array, T value) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            // First boundary: add 1/3 of length to start
            int middle1 = left + (right - left) / 3;
            // Second boundary: add 2/3 of length to start
            int middle2 = left + 2 * (right - left) / 3;

            int cmp1 = value.compareTo(array[middle1]);
            int cmp2 = value.compareTo(array[middle2]);

            if (cmp1 == 0) {
                return left;
            }
            if (cmp2 == 0) {
                return right;
            }

            if (array[middle1].compareTo(value) <= 0) {
                left = middle1;
            } else {
                right = middle2;
            }
        }
        return -1;
    }

    /**
     * Drive program.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Generate data.
        Random random = new Random();
        int size = 200;
        int maxValue = 10000;
        Integer[] integers = Stream.generate(() -> random.nextInt(maxValue)).limit(size).sorted()
                .toArray(Integer[]::new);
        Integer targetValue = integers[random.nextInt(size - 1)];

        // Start here.
        TernarySearch ternarySearch = new TernarySearch();
        int foundIndex = ternarySearch.find(integers, targetValue);
        System.out.print(String.format("Target value : %d. Found %d at index %d. Array length : %d.", targetValue,
                integers[foundIndex], foundIndex, size));

        // Check the result using system method.
        int checkIndex = Arrays.binarySearch(integers, targetValue);
        System.out.println(String.format("\nFound by system method at index %d. Is equal: %b", checkIndex,
                foundIndex == checkIndex));
    }
}