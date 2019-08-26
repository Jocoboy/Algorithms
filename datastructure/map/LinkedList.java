package datastructure.map;

class Node {

    int value;
    Node next;

    Node(int value) {
        this.value = value;
        this.next = null;
    }
}

public class LinkedList {

    private Node head;

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getSize() {
        if (head == null) {
            return 0;
        } else {
            Node curNode = head;
            int size = 1;
            while (curNode.next != null) {
                curNode = curNode.next;
                size++;
            }
            return size;
        }
    }

    public void insert(int value) {
        Node newNode = new Node(value);

        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void delete(int value) {
        if (isEmpty()) {
            throw new RuntimeException("Attempt to delete an element from an empty list.");
        } else {
            Node curNode = head;
            if (curNode.value == value) {
                head = curNode.next;
                return;
            } else {
                while (curNode.next.next != null) {
                    if (curNode.next.value == value) {
                        curNode.next = curNode.next.next;
                        return;
                    }
                    System.out.println("Element is non-existent.");
                }
            }
        }
    }

    public void display() {
        Node curNode = head;
        while (curNode != null) {
            System.out.printf("%d ", curNode.value);
            curNode = curNode.next;
        }
        System.out.println();
    }
}