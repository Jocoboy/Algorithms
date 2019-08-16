package sort;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import static sort.util.Utils.*;

/**
 * Optimize SelectionSort.
 * 
 * @see Sort
 */
public class HeapSort implements Sort {

    private static class Heap<T extends Comparable<T>> {

        private T[] heap;

        /**
         * constructor
         * 
         * @param heap - an unsorted array
         */
        public Heap(T[] heap) {
            this.heap = heap;
        }

        /**
         * 
         * @param rootIndex - index of root
         */
        private void makeMinHeap(int rootIndex) {
            // Feature of complete binary tree.
            int leftIndex = (rootIndex << 1) + 1;
            int rightIndex = (rootIndex << 1) + 2;
            boolean hasLeftChild = leftIndex < heap.length;
            boolean hasRightChild = rightIndex < heap.length;
            if (hasRightChild) {
                makeMinHeap(leftIndex);
                makeMinHeap(rightIndex);
                heapSubtree(rootIndex, heap.length - 1);
            } else if (hasLeftChild) {
                heapSubtree(rootIndex, heap.length - 1);
            }
        }

        /**
         * Heapify subtree from top as root to bottom as last child.
         * 
         * @param rootIndex      - index of root
         * @param lastChildIndex - index of last child
         */
        private void heapSubtree(int rootIndex, int lastChildIndex) {
            // Feature of complete binary tree.
            int leftIndex = (rootIndex << 1) + 1;
            int rightIndex = (rootIndex << 1) + 2;
            // Left and right child both exist.
            if (rightIndex <= lastChildIndex) {
                // In that case, find the min child element and check whether to swap with root
                // element or not.
                if (less(heap[leftIndex], heap[rightIndex]) && less(heap[leftIndex], heap[rootIndex])) {
                    swap(heap, leftIndex, rootIndex);
                    heapSubtree(leftIndex, lastChildIndex);
                } else if (less(heap[rightIndex], heap[rootIndex])) {
                    swap(heap, rightIndex, rootIndex);
                    heapSubtree(rightIndex, lastChildIndex);
                }
            }
            // Only left child exists.
            else if (leftIndex <= lastChildIndex) {
                if (less(heap[leftIndex], heap[rootIndex])) {
                    swap(heap, leftIndex, rootIndex);
                    heapSubtree(leftIndex, lastChildIndex);
                }
            }
        }

        /**
         * 
         * @param size - size of heap
         * @return root of heap
         */
        private T getRoot(int size) {
            swap(heap, 0, size);
            heapSubtree(0, size - 1);
            return heap[size];
        }
    }

    /**
     * Sort the array in increasing order.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        return sort(Arrays.asList(unsorted)).toArray(unsorted);
    }

    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> unsorted) {
        @SuppressWarnings("unchecked")
        Heap<T> heap = new Heap<>(unsorted.toArray((T[]) new Comparable[unsorted.size()]));

        // Make min heap using 0 as root index.
        heap.makeMinHeap(0);
        int size = unsorted.size();
        List<T> sorted = new ArrayList<>(size);
        while (size > 0) {
            T min = heap.getRoot(--size);
            sorted.add(min);
        }
        return sorted;
    }

    /**
     * Drive program.
     * 
     * @param args
     */
    public static void main(String args[]) {
        HeapSort heapSort = new HeapSort();

        // Integer Input
        Integer[] integers = { 77, 5, 133, 197, 1586, -17, -111, 0, 19, 21 };
        heapSort.sort(integers);
        print(integers);

        // String Input
        String[] strings = { "g", "f", "z", "w", "A", "o", "P" };
        print(heapSort.sort(strings));
    }
}