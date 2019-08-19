package search;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Interpolation search is a method of retrieving a desired record by key in an ordered file
 * by using the value of the key and the statistical distribution of the keys.
 * <p>
 * Worst-case performance	 O(n)
 * Best-case performance	O(1)
 * Average performance	O(log(log(n))) if the elements are uniformly distributed (O(n) if not )
 * Worst-case space complexity	O(1)
 * 
 * @see Search 
 */

public class InterpolationSearch implements Search {

    @Override
    public <T extends Comparable<T>> int find(T[] array, T value) {
        return search((Integer[]) array, (Integer) value);
    }

    /**
     * 
     * @param <T>   - any comparable type
     * @param array - an array where the element should be found
     * @param value - an element to be found
     * @return first found index of the element
     */
    private int search(Integer[] array, Integer value) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right && value >= array[left] && value <= array[right]) {
            int pos = left + (right - left) / (array[right] - array[left]) * (value - array[left]);
            if (array[pos] < value) {
                left = pos + 1;
            } else if (array[pos] > value) {
                right = pos - 1;
            } else {
                return pos;
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
        InterpolationSearch interpolationSearch = new InterpolationSearch();
        int foundIndex = interpolationSearch.find(integers, targetValue);
        System.out.print(String.format("Target value : %d. Found %d at index %d. Array length : %d.", targetValue,
                integers[foundIndex], foundIndex, size));

        // Check the result using system method.
        int checkIndex = Arrays.binarySearch(integers, targetValue);
        System.out.println(String.format("\nFound by system method at index %d. Is equal: %b", checkIndex,
                foundIndex == checkIndex));
    }
}