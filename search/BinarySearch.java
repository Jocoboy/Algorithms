package search;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Binary search is one of the most popular algorithms.
 * The algorithm finds the position of a target value within a sorted array.
 * <p>
 * Worst-case performance	O(log n)
 * Best-case performance	O(1)
 * Average performance	O(log n)
 * Worst-case space complexity	O(1)
 * 
 * @see Search
 */
public class BinarySearch implements Search {

    @Override
    public <T extends Comparable<T>> int find(T[] array, T value) {
        return search(array, value, 0, array.length);
    }

    /**
     * 
     * @param <T>   - any comparable type
     * @param array - an array where the element should be found
     * @param value - an element to be found
     * @param left  - the lower bound
     * @param right - the upper bound
     * @return first found index of the element
     */
    private <T extends Comparable<T>> int search(T[] array, T value, int left, int right) {
        if (left > right)
            return -1;

        int middle = (left + right) >> 1;
        int cmp = value.compareTo(array[middle]);

        if (cmp < 0) {
            return search(array, value, left, middle - 1);
        } else if (cmp > 0) {
            return search(array, value, middle + 1, right);
        }
        return middle;
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