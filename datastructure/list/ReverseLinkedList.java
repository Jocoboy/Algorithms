package datastructure.list;

public class ReverseLinkedList<T> {

    private class Node {
        T data;
        Node next;

        /**
         * constructor
         * 
         * @param data - input data
         */
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    /**
     * constructor
     */
    public ReverseLinkedList() {
        head = new Node(null);
        head.next = head;
        size = 1;
    }

    /**
     * Append a new node to the linkedlist.
     * 
     * @param data - input data
     * @return true if successfully appended
     */
    public boolean append(T data) {
        Node curNode = head;
        Node newNode = new Node(data);
        newNode.next = curNode.next;
        curNode.next = newNode;
        size++;
        return true;
    }

    /**
     * Reverse the linkedlist.
     * 
     * @return true if reverse successfully
     */
    public boolean reverse() {
        Node p = head;
        Node q = head.next;
        Node r = head.next.next;
        head.next = null;
        int localSize = this.size;
        while (localSize-- > 1) {
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        head.next = p;
        return true;
    }

    /**
     * Traversal and print the linkedlist.
     */
    public void display() {
        Node curNode = head;
        int localSize = this.size;
        while (localSize-- > 1) {
            curNode = curNode.next;
            System.out.print(curNode.data + " ");
        }
        System.out.println();
    }

    /**
     * Drive program.
     * 
     * @param args
     */
    public static void main(String[] args) {

        ReverseLinkedList<Integer> integerList = new ReverseLinkedList<Integer>();
        for (int i = 10; i >= 0; i--) {
            integerList.append(i * 2 + 1);
        }
        integerList.display();
        if (integerList.reverse()) {
            integerList.display();
        }

        ReverseLinkedList<Character> characterList = new ReverseLinkedList<Character>();
        for (int i = 0; i < 26; i++) {
            characterList.append((char) ('a' + i));
        }
        characterList.display();
        if (characterList.reverse()) {
            characterList.display();
        }
    }
}