package datastructure.queue;

public class CircularQueue {

    private int maxSize;
    private int[] queueArray;
    private int front;
    private int rear;

    public CircularQueue(int initSize) {
        maxSize = initSize + 1;
        queueArray = new int[initSize + 1];
        front = 0;
        rear = 0;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean offer(int value) {
        if (isFull()) {
            return false;
        }
        queueArray[rear] = value;
        rear = (rear + 1) % maxSize;
        return true;
    }

    public int poll() {
        if (isEmpty()) {
            return -1;
        }
        int temp = queueArray[front];
        front = (front + 1) % maxSize;
        return temp;
    }

    public int peek() {
        return queueArray[front];
    }

    public int getSize() {
        return (rear - front + maxSize) % maxSize;
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(4);
        queue.offer(10);
        queue.offer(2);
        queue.offer(5);
        queue.offer(3);
        System.out.println(queue.isFull()); // true
        queue.poll();
        System.out.println(queue.getSize()); // 3
        queue.offer(7);
        System.out.println(queue.peek());// 2
    }
}