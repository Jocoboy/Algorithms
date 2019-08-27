package datastructure.list;

/**
 * A linked list is similar to an array, it holds values. However, links in a
 * linked list do not have indexes. With a linked list you do not need to
 * predetermine it's size as it grows and shrinks as it is edited. This is an
 * example of a double ended, doubly linked list. Each link references the next
 * link and the previous one.
 */
public class DoublyLinkedList {

    class Node {

        int value;
        Node next;
        Node previous;
    
        public Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Insert an element at the head.
     * 
     * @param value - element to be inserted
     */
    public void insertHead(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            // Set the first element to be the tail.
            tail = newNode;
        } else {
            head.previous = newNode; // newNode <-- curHead(head)
        }
        newNode.next = head; // newNode <--> curHead(head)
        head = newNode; // newNode(curHead(head)) <--> preHead
    }

    /**
     * Insert an element at the tail.
     * 
     * @param value - element to be inserted
     */
    public void insertTail(int value) {
        Node newNode = new Node(value);
        newNode.next = null;// curTail(tail) ? newNode --> null
        // Set the element to be the tail directly and set head point to tail.
        if (isEmpty()) {
            tail = newNode;
            head = tail;
        } else {
            tail.next = newNode;// curTail(tail) --> newNode --> null
            newNode.previous = tail;// curTail(tail) <--> newNode --> null
            tail = newNode; // preTail <--> newNode(curTail(tail)) --> null
        }
    }

    /**
     * Insert an element and reorder.
     * 
     * @param value - element to be inserted
     */
    public void insert(int value) {
        Node newNode = new Node(value);
        Node curNode = head;
        while (curNode != null && curNode.value < value) {
            curNode = curNode.next;
        }
        if (curNode == head) {
            insertHead(value);
        } else if (curNode == null) {
            insertTail(value);
        } else {
            // init: 1 <--> 2(curNode) <--> 3
            newNode.previous = curNode.previous; // 1 <-- newNode
            curNode.previous.next = newNode; // 1 <--> newNode
            newNode.next = curNode; // 1 <--> newNode --> 2(curNode) <--> 3
            curNode.previous = newNode; // 1 <--> newNode <--> 2(curNode) <--> 3
        }
    }

    /**
     * Delete the element at the head.
     * 
     * @return - new head
     */
    public Node deleteHead() {
        Node temp = head;
        head = head.next; // preHead <--> 2ndElement(curHead(head))
        head.previous = null;// preHead --> 2ndElement(curHead(head))
        if (head == null) {
            tail = null;
        }
        return temp;
    }

    /**
     * Delete the element at the tail.
     * 
     * @return - new tail
     */
    public Node deleteTail() {
        Node temp = tail;
        tail = tail.previous; // 2ndElement(inverted order)(curTail(tail)) <--> preTail --> null
        tail.next = null;// 2ndElement(inverted order)(curTail(tail)) --> null
        if (tail == null) {
            head = null;
        }
        return temp;
    }

    /**
     * Delete an element from somewhere.
     * 
     * @param value - element to be deleted
     */
    public void delete(int value) {
        Node curNode = head;
        while (curNode.value != value) {
            if (curNode != tail) {
                curNode = curNode.next;
            } else {
                throw new RuntimeException("The element to be deleted does not exist!");
            }
        }
        if (curNode == head) {
            deleteHead();
        } else if (curNode == tail) {
            deleteTail();
        } else {
            // init: 1 <--> 2(curNode) <--> 3
            curNode.previous.next = curNode.next; // 1 --> 3
            curNode.next.previous = curNode.previous; // 1 <--> 3
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
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        doublyLinkedList.insertHead(13);
        doublyLinkedList.insertHead(7);
        doublyLinkedList.insertHead(10);
        doublyLinkedList.display(); // null <-- 10(head) <--> 7 <--> 13(tail) --> null

        doublyLinkedList.insertTail(11);
        doublyLinkedList.display(); // null <-- 10(head) <--> 7 <--> 13 <--> 11(tail) --> null

        doublyLinkedList.deleteTail();
        doublyLinkedList.display();// null <-- 10(head) <--> 7 <--> 13(tail) --> null

        doublyLinkedList.delete(7);
        doublyLinkedList.display();// null <-- 10(head) <--> 13(tail) --> null

        doublyLinkedList.insert(23);
        doublyLinkedList.insert(67);
        doublyLinkedList.insert(3);// null <-- 3(head) <--> 10 <--> 13 <--> 23 <--> 67(tail) --> null
        doublyLinkedList.display();

        doublyLinkedList.deleteHead();
        doublyLinkedList.display();// null <-- 10(head) <--> 13 <--> 23 <--> 67(tail) --> null
    }
}