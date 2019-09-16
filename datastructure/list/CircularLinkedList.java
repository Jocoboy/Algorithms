package datastructure.list;

public class CircularLinkedList<T> {

    class Node {

        T value;
        Node next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private int size;
    // refer to dummy node
    private Node head;

    /**
     * Make a dummy node for circular linked list implementation with reduced error
     * catching as our list will never be empty。
     */
    public CircularLinkedList() {
        head = new Node(null);
        head.next = head;
        size = 1;
    }

    public int getSize() {
        return size;
    }

    public void insert(T value, int pos) {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException("Position cannot be greater than size or negative.");
        }
        if (value == null) {
            throw new NullPointerException("Attempt to add null node to the list.");
        }

        // last node --> head
        Node curNode = head;
        Node newNode = new Node(value);
        for (int i = 1; i < pos; i++) {
            curNode = curNode.next;
        }
        newNode.next = curNode.next;
        curNode.next = newNode;
        size++;
    }

    public T delete(int pos) {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException("Position cannot be greater than size or negative.");
        }
        Node curNode = head;
        for (int i = 1; i < pos; i++) {
            curNode = curNode.next;
        }
        Node deletedNode = curNode.next;
        curNode.next = curNode.next.next;
        size--;
        return deletedNode.value;
    }

    public void display() {
        int localSize = this.size;
        Node curNode = head.next;
        while (localSize-- > 1) {
            System.out.println(curNode.value + " ");
            curNode = curNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<Integer>();
        circularLinkedList.insert(7, 1);
        circularLinkedList.insert(21, 2);
        circularLinkedList.insert(42, 3);
        System.out.println("Current size : " + circularLinkedList.getSize());
        circularLinkedList.insert(84, 2);
        System.out.println("After insertion:");
        circularLinkedList.display();

        System.out.println("deleted node : " + circularLinkedList.delete(1));
        System.out.println("After deletion:");
        circularLinkedList.display();

    }
}