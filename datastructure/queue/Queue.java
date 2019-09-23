package datastructure.queue;

class Queue {

    private int maxSize;
    private int[] queueArray;
    // lower bound of the queue
    private int front;
    // upper bound of the queue
    private int rear;
    private int curSize;

    public Queue(int initSize) {
        maxSize = initSize;
        queueArray = new int[initSize];
        front = 0;
        rear = -1;
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
        if (rear == maxSize - 1) {
            rear = -1;
        }
        rear++;
        queueArray[rear] = value;
        curSize++;
        return true;
    }

    public int poll() {
        if (isEmpty()) {
            return -1;
        }
        int temp = queueArray[front];
        front++;
        if (front == maxSize) {
            front = 0;
        }
        curSize--;
        return temp;
    }

    public int peek() {
        return queueArray[front];
    }

    public int getSize() {
        return curSize;
    }

    public static void main(String[] args) {
        Queue queue = new Queue(4);
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