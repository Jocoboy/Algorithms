package datastructure.list;

/**
 * A linked list is similar to an array, it hold values.However, links in a
 * linked list do not have indexes. With a linked list you do not need to
 * predetermine it's size as it grows and shrinks as it is edited.
 */
public class SinglyLinkedList {

    class Node {

        int value;
        Node next;
    
        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

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

    public void insertHead(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }

    public void insertNth(int value, int pos) {
        if (pos < 0 || pos > getSize()) {
            throw new RuntimeException("Position less than zero or position more than the count of list.");
        } else if (pos == 0) {
            insertHead(value);
        } else {
            Node curNode = head;
            Node newNode = new Node(value);
            for (int i = 1; i < pos; i++) {
                curNode = curNode.next;
            }
            newNode.next = curNode.next;
            curNode.next = newNode;
        }
    }

    public void deleteHead() {
        if (isEmpty()) {
            throw new RuntimeException("Attempt to delete an element from an empty list.");
        }
        head = head.next;
    }

    public void deleteNth(int pos) {
        if (pos < 0 || pos > getSize()) {
            throw new RuntimeException("Position less than zero or position more than the count of list.");
        } else if (pos == 0) {
            deleteHead();
        } else {
            Node curNode = head;
            for (int i = 1; i < pos; i++) {
                curNode = curNode.next;
            }
            curNode.next = curNode.next.next;
        }
    }

    public void display() {
        Node curNode = head;
        while (curNode != null) {
            System.out.println(curNode.value + " ");
            curNode = curNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        assert singlyLinkedList.isEmpty();

        singlyLinkedList.insertHead(5);
        singlyLinkedList.insertHead(7);
        singlyLinkedList.insertHead(10);
        singlyLinkedList.display(); // 10 7 5

        singlyLinkedList.deleteHead();
        singlyLinkedList.display();// 7 5

        singlyLinkedList.insertNth(11, 2);
        singlyLinkedList.display();// 7 5 11

        singlyLinkedList.deleteNth(1);

        singlyLinkedList.display();// 7 11
    }
}
