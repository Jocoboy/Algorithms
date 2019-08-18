package search;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 * A ternary search algorithm is a technique in computer science for finding the minimum or maximum of a unimodal function.
 * The algorithm determines either that the minimum or maximum cannot be in the first third of the domain
 * or that it cannot be in the last third of the domain, then repeats on the remaining third.
 * <p>
 * Worst-case performance	Θ(log3(n))
 * Best-case performance	O(1)
 * Average performance	Θ(log3(n))
 * Worst-case space complexity	O(1)
 * 
 * @see Search
 */
public class TernarySearch implements Search {

    @Override
    public <T extends Comparable<T>> int find(T[] array, T value) {
        return search(array, value, 0, array.length - 1);
    }

    /**
     * 
     * @param <T>   - any comparable type
     * @param array - an array where the element should be found
     * @param value - an element to be found
     * @param left  - the lower bound
     * @param right - the upper bound
     * @return
     */
    private <T extends Comparable<T>> int search(T[] array, T value, int left, int right) {
        if (left > right)
            return -1;
        // First boundary: add 1/3 of length to start
        int middle1 = left + (right - left) / 3;
        // Second boundary: add 2/3 of length to start
        int middle2 = left + 2 * (right - left) / 3;
        
        int cmp1 = value.compareTo(array[middle1]);
        int cmp2 = value.compareTo(array[middle2]);

        if (cmp1 == 0) {
            return middle1;
        } else if (cmp2 == 0) {
            return middle2;
        } else if (cmp1 < 0) {
            return search(array, value, left, middle1 - 1);
        } else if (cmp2 > 0) {
            return search(array, value, middle2 + 1, right);
        } else {
            return search(array, value, middle1, middle2);
        }
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