package sort;

import static sort.util.Utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;

/**
 * @see Sort
 */

public class CountingSort implements Sort {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        return sort(Arrays.asList(unsorted)).toArray(unsorted);
    }

    /**
     * Sorts the list in increasing order.
     * 
     * @param unsorted - the list to be sorted
     * 
     */
    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> unsorted) {
        Map<T, Integer> freq_map = new TreeMap<>();
        List<T> sorted = new ArrayList<>(unsorted.size());

        // Use list elements as keys in the frequency map.
        unsorted.forEach(key -> freq_map.put(key, freq_map.getOrDefault(key, 0) + 1));

        // Fill sorted list.
        for (Map.Entry<T, Integer> element : freq_map.entrySet()) {
            for (int i = 0; i < element.getValue(); i++) {
                sorted.add(element.getKey());
            }
        }
        return sorted;
    }

    public static void main(String[] args) {
        CountingSort countingSort = new CountingSort();

        // Integer Input
        Integer[] integers = { 77, 5, 133, 197, 1586, -17, -111, 0, 19, 21 };
        countingSort.sort(integers);
        print(integers);

        // String Input
        String[] strings = { "g", "f", "z", "w", "A", "o", "P" };
        print(countingSort.sort(strings));
    }
}