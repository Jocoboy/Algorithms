package datastructure.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Heap tree where a node's key is always higher than or equal to its children's
 * and lower than or equal to its parent's.
 */
public class MaxHeap implements Heap {

    /**
     * container to store heap element
     */
    private final List<HeapElement> maxHeap;

    /**
     * constructor
     * 
     * @param listElements
     */
    public MaxHeap(List<HeapElement> listElements) {
        maxHeap = new ArrayList<>();
        for (HeapElement element : listElements) {
            if (element != null) {
                insertElement(element);
            } else {
                System.out.println("Null element, not added to heap.");
            }
        }
        if (maxHeap.size() == 0) {
            System.out.println("Empty heap exists.");
        }
    }

    /**
     * Get the element at a given index.Note that index of list is equal to index-1
     * of heap. Index of heap element start from 1.
     * 
     * @param index - index of element
     * @return nth-Element
     */
    public HeapElement getElement(int index) {
        if (index <= 0 || index > maxHeap.size()) {
            throw new IndexOutOfBoundsException("Index out of heap range.");
        }
        return maxHeap.get(index - 1);
    }

    /**
     * 
     * @param index - index of element
     * @return key of nth-Element
     */
    private double getElementKey(int index) {
        return maxHeap.get(index - 1).getKey();
    }

    /**
     * 
     * @param index1 - first element index
     * @param index2 - second element index
     */
    private void swap(int index1, int index2) {
        HeapElement tempElement = maxHeap.get(index1 - 1);
        maxHeap.set(index1 - 1, maxHeap.get(index2 - 1));
        maxHeap.set(index2 - 1, tempElement);
    }

    public int getSize() {
        return maxHeap.size();
    }

    /**
     * Toggle an element up to its right place as long as its key is higher than its
     * parent's.
     * 
     * @param index - target element index
     */
    private void toggleUp(int index) {
        boolean isWrongOrder = getElementKey(index) > getElementKey(Math.max((int) Math.floor(index >> 1), 1));
        while ((index >> 1) >= 1 && isWrongOrder) {
            // Here we swap it with the parent.
            swap(index, (int) Math.floor(index >> 1));
            index = (int) Math.floor(index >> 1);
            isWrongOrder = getElementKey(index) > getElementKey(Math.max((int) Math.floor(index >> 1), 1));
        }
    }

    /**
     * Toggle an element down to its right place as long as its key is lower than
     * any of its children's.
     * 
     * @param index - target element index
     */
    private void toggleDown(int index) {
        boolean isWrongOrder = getElementKey(index) < getElementKey(Math.min(index << 1, maxHeap.size()));
        while ((index << 1) <= maxHeap.size() && isWrongOrder) {
            // Here we swap it with the child for higher key.
            if ((index << 1) < maxHeap.size() && (getElementKey((index << 1) + 1) > getElementKey(index << 1))) {
                swap(index, (index << 1) + 1);
                index = (index << 1) + 1;
            } else {
                swap(index, index << 1);
                index <<= 1;
            }
            isWrongOrder = getElementKey(index) < getElementKey(Math.min(index << 1, maxHeap.size()));
        }
    }

    @Override
    public HeapElement getElement() throws EmptyHeapException {
        try {
            HeapElement result = maxHeap.get(0);
            deleteElement(0);
            return result;
        } catch (Exception e) {
            throw new EmptyHeapException("Attempt to get an element from an empty heap.");
        }
    }

    @Override
    public void insertElement(HeapElement element) {
        maxHeap.add(element);
        toggleUp(maxHeap.size());
    }

    @Override
    public void deleteElement(int index) {
        if (maxHeap.isEmpty()) {
            try {
                throw new EmptyHeapException("Attempt to delete an element from an empty heap.");
            } catch (EmptyHeapException e) {
                e.printStackTrace();
            }
        }
        if (index <= 0 || index > maxHeap.size()) {
            throw new IndexOutOfBoundsException("Index out of heap range.");
        }

        // The last element in heap replaces the one to be deleted.
        maxHeap.set(index - 1, getElement(maxHeap.size()));
        maxHeap.remove(maxHeap.size() - 1);

        if (getElementKey(index) > getElementKey((int) Math.floor(index >> 1))) {
            toggleUp(index);
        } else if (((index << 1) <= maxHeap.size()) && (getElementKey(index) < getElementKey(index << 1))) {
            toggleDown(index);
        }
    }

    public static void main(String[] args) {
        List<HeapElement> listElements = new ArrayList<HeapElement>();
        listElements.add(new HeapElement(36));
        listElements.add(new HeapElement(19));
        listElements.add(new HeapElement(100));
        listElements.add(new HeapElement(17));
        listElements.add(new HeapElement(3));
        listElements.add(new HeapElement(25));
        listElements.add(new HeapElement(1));
        listElements.add(new HeapElement(2));
        listElements.add(new HeapElement(7));
        MaxHeap maxHeap = new MaxHeap(listElements);
        System.out.println("Original Heap.");
        for (int i = 1; i <= maxHeap.getSize(); i++) {
            System.out.println(maxHeap.getElement(i).getKey()); // 100.0 19.0 36.0 17.0 3.0 25.0 1.0 2.0 7.0
        }
        maxHeap.deleteElement(3);
        System.out.println("After deletions...");
        for (int i = 1; i <= maxHeap.getSize(); i++) {
            System.out.println(maxHeap.getElementKey(i)); // 100.0 19.0 25.0 17.0 3.0 7.0 1.0 2.0
        }
    }
}