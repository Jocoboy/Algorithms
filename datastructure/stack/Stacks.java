package datastructure.stack;

class Stack {

    private int maxSize;
    private int[] stackArray;
    // top = curSize-1
    private int top;

    public Stack(int initSize) {
        maxSize = initSize;
        stackArray = new int[initSize];
        top = -1;
    }

    public boolean isFull() {
        return top + 1 == maxSize;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Enlarge a stack when it is full.
     * 
     * @param newSize
     */
    private void resize(int newSize) {
        int[] tempArray = new int[newSize];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = stackArray[i];
            stackArray = tempArray;
        }
        maxSize = newSize;
    }

    public int push(int value) {
        if (!isFull()) {
            top++;
            stackArray[top] = value;
            return value;
        } else {
            resize(maxSize * 2);
            push(value);
        }
        return -1;
    }

    public int pop() {
        if (!isEmpty()) {
            return stackArray[top--];
        }
        if (top < maxSize / 4) {
            resize(maxSize / 2);
            return pop();
        }
        return -1;
    }

    public int peek() {
        if (!isEmpty()) {
            return stackArray[top];
        } else {
            System.out.println("Attempt to peek an element from an empty stack.");
            return -1;
        }
    }
}

public class Stacks {
    public static void main(String[] args) {
        Stack stack = new Stack(4);

        stack.push(5);
        stack.push(8);
        stack.push(2);
        stack.push(9);

        System.out.println("Array Stack Implementation.");
        System.out.println(stack.isFull()); // true
        System.out.println(stack.isEmpty()); // false
        System.out.println(stack.peek()); // 9
        System.out.println(stack.pop()); // 9
        System.out.println(stack.peek()); // 2
    }
}