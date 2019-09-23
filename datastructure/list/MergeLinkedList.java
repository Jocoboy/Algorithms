package datastructure.list;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MergeLinkedList {

    private class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node[] headNodes;
    private int[] sizes;
    private int n;
    private Node finalHead;

    public MergeLinkedList(int n) {
        this.n = n;
        headNodes = new Node[n];
        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            headNodes[i] = new Node(0);
            sizes[i] = 1;
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            while (true) {
                int data = scanner.nextInt();
                if (data == -1)
                    break;
                Node curNode = headNodes[i];
                Node newNode = new Node(data);
                newNode.next = curNode.next;
                curNode.next = newNode;
                sizes[i]++;
            }
            this.display(i);
        }
        finalHead = merge(headNodes);
        this.display();
        scanner.close();
    }

    public void display(int i) {
        Node curNode = headNodes[i];
        int localSize = this.sizes[i];
        while (localSize-- > 1) {
            curNode = curNode.next;
            System.out.print(curNode.data + " ");
        }
        System.out.println();
    }

    public void display() {
        Node curNode = finalHead;
        for (int i = 0; i < n - 1; i++) {
            curNode = curNode.next;
        }
        int localSize = 0;
        for (int i = 0; i < n; i++) {
            localSize += sizes[i];
        }
        while (localSize-- > n) {
            curNode = curNode.next;
            System.out.print(curNode.data + " ");
        }
        System.out.println();
    }

    /**
     * Merge n sorted linkedlists.
     * 
     * @param headNodes
     * @return head of the merged linkedlist
     */
    public Node merge(Node[] headNodes) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(x -> x.data));
        // Add head nodes of all linkedlists in min heap.
        minHeap.addAll(Arrays.asList(headNodes).subList(0, n));
        // Make the smallest head node among n linkedlists be the new head.
        Node head = minHeap.poll();
        minHeap.add(head.next);
        Node curNode = head;

        while (!minHeap.isEmpty()) {
            // Temp node polled from min heap is the succeed of current node.
            Node tempNode = minHeap.poll();
            curNode.next = tempNode;
            curNode = tempNode;

            // If the current linkedlist is not empty, update the min heap.
            if (tempNode.next != null) {
                minHeap.add(tempNode.next);
            }
        }

        return head;
    }

    public static void main(String[] args) {
        MergeLinkedList mergeLinkedList = new MergeLinkedList(4);
        mergeLinkedList.run();
    }
}