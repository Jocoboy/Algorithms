package search;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Linear search is the easiest search algorithm. 
 * It works with sorted and unsorted arrays (an binary search works only with sorted array). 
 * This algorithm just compares all elements of an array to find a value.
 * <p>
 * Worst-case performance	O(n)
 * Best-case performance	O(1)
 * Average performance	O(n)
 * Worst-case space complexity
 * 
 * @see Sort
 */
public class LinearSearch implements Search {

    @Override
    public <T extends Comparable<T>> int find(T[] array, T value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(value) == 0) {
                return i;
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
        Integer[] integers = Stream.generate(() -> random.nextInt(maxValue)).limit(size).toArray(Integer[]::new);
        Integer targetValue = integers[random.nextInt(size - 1)];

        // Start here.
        LinearSearch linearSearch = new LinearSearch();
        int foundIndex = linearSearch.find(integers, targetValue);
        System.out.print(String.format("Target value : %d. Found %d at index %d. Array length : %d.", targetValue,
                integers[foundIndex], foundIndex, size));
    }
}