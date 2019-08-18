package search;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * This class represents iterative version {@link BinarySearch}.
 * Iterative binary search is likely to have lower constant factors because it doesn't involve the overhead of manipulating the call stack.
 * But in java the recursive version can be optimized by the compiler to this version.
 * <p>
 * Worst-case performance	O(log n)
 * Best-case performance	O(1)
 * Average performance	O(log n)
 * Worst-case space complexity	O(1)
 * 
 * @see Search
 * @see BinarySearch
 */
public class IterativeBinarySearch implements Search {

    @Override
    public <T extends Comparable<T>> int find(T[] array, T value) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int middle = (left + right) >> 1;
            int cmp = value.compareTo(array[middle]);
            if (cmp < 0) {
                right = --middle;
            } else if (cmp > 0) {
                left = ++middle;
            } else {
                return middle;
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
        Random random = ThreadLocalRandom.current();
        int size = 200;
        int maxValue = 10000;
        Integer[] integers = IntStream.generate(() -> random.nextInt(maxValue)).limit(size).sorted().boxed()
                .toArray(Integer[]::new);
        Integer targetValue = integers[random.nextInt(size - 1)];

        // Start here.
        BinarySearch binarySearch = new BinarySearch();
        int foundIndex = binarySearch.find(integers, targetValue);
        System.out.print(String.format("Target value : %d. Found %d at index %d. Array length : %d.", targetValue,
                integers[foundIndex], foundIndex, size));

        // Check the result using system method.
        int checkIndex = Arrays.binarySearch(integers, targetValue);
        System.out.println(String.format("\nFound by system method at index %d. Is equal: %b", checkIndex,
                foundIndex == checkIndex));
    }
}