package datastructure.queue;

/**
 * A priority queue adds elements into positions based on their priority.
 * So the most important elements are placed at the front (on the top).
 * Queue in theory has no fixed size but when using an array implementation it does.
 */
class PriorityQueue {

    private int maxSize;
    private int[] queueArray;
    private int curSize;

    public PriorityQueue(int initSize) {
        maxSize = initSize;
        queueArray = new int[initSize];
        curSize = 0;
    }

    public boolean isFull() {
        return curSize == maxSize;
    }

    public boolean isEmpty() {
        return curSize == 0;
    }

    public boolean offer(int value) {
        if (isFull()) {
            return false;
        }
        if (curSize == 0) {
            queueArray[0] = value;
        } else {
            int i = curSize;
            while (i > 0 && queueArray[i - 1] > value) {
                queueArray[i] = queueArray[i - 1];
                i--;
            }
            queueArray[i] = value;
        }
        curSize++;
        return true;
    }

    public int poll() {
        return queueArray[--curSize];
    }

    public int peek() {
        return queueArray[curSize - 1];
    }

    public int getSize() {
        return curSize;
    }
}

public class PriorityQueues {
    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue(4);
        priorityQueue.offer(10);
        priorityQueue.offer(2);
        priorityQueue.offer(5);
        priorityQueue.offer(3);

        for (int i = priorityQueue.getSize(); i >= 0; i--) {
            System.out.println(priorityQueue.poll() + " ");// 10 5 3 2
        }
    }
}