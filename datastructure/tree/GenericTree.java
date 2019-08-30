package datastructure.tree;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A generic tree is a tree which can have as many children as it can be. It
 * might be possible that every node present is directly connected to root node.
 */
public class GenericTree {

    class Node {
        int value;
        ArrayList<Node> child = new ArrayList<>();
    }

    private Node root;

    public GenericTree() {
        Scanner scanner = new Scanner(System.in);
        root = createTree(null, 0, scanner);
    }

    private Node createTree(Node node, int childIndex, Scanner scanner) {
        if (node == null) {
            System.out.println("Enter root's value");
        } else {
            System.out.printf("Enter value of parent of index %d %d\n", node.value, childIndex);
        }
        node = new Node();
        node.value = scanner.nextInt();
        System.out.println("Enter number of children");
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++) {
            Node child = createTree(node, i, scanner);
            node.child.add(child);
        }
        return node;
    }

    public int getSize(Node parent) {
        int size = 0;
        for (int i = 0; i < parent.child.size(); i++) {
            size += getSize(parent.child.get(i));
        }
        return size + 1;
    }

    public Node getRoot() {
        return root;
    }

    public void display(Node parent) {
        System.out.print(parent.value + "=>");
        for (int i = 0; i < parent.child.size(); i++) {
            System.out.print(parent.child.get(i).value + " ");
        }
        System.out.println(".");
        for (int i = 0; i < parent.child.size(); i++) {
            display(parent.child.get(i));
        }
    }

    /**
     * Function to compute maximum value in the generic tree.
     * 
     * @param parent - starting point
     * @param maxi   - local max value
     * @return local value
     */
    public int getMax(Node parent, int maxi) {
        maxi = Math.max(maxi, parent.value);
        for (int i = 0; i < parent.child.size(); i++) {
            maxi = getMax(parent.child.get(i), maxi);
        }
        return maxi;
    }

    /**
     * Function to compute height of the generic tree.
     * 
     * @param parent - starting point
     * @return local height
     */
    public int getHeight(Node parent) {
        int height = 0;
        for (int i = 0; i < parent.child.size(); i++) {
            height = Math.max(getHeight(parent.child.get(i)), height);
        }
        return height + 1;
    }

    public void findDepth(int depth) {
        findDepth(root, depth);
    }

    /**
     * Function to print node of k-depth.
     * 
     * @param parent - starting point
     * @param depth  - depth to be found
     */
    private void findDepth(Node parent, int depth) {
        if (depth == 0) {
            System.out.println(parent.value);
            return;
        }
        for (int i = 0; i < parent.child.size(); i++) {
            findDepth(parent.child.get(i), depth - 1);
        }
    }

    public boolean find(int value) {
        return find(root, value);
    }

    /**
     * Function to find whether a number is present in the generic tree or not.
     * 
     * @param parent - starting point
     * @param value  - value to be found
     * @return true if found
     */
    private boolean find(Node parent, int value) {
        if (parent.value == value) {
            return true;
        }
        for (int i = 0; i < parent.child.size(); i++) {
            if (find(parent.child.get(i), value)) {
                return true;
            }
        }
        return false;
    }

    public void removeLeaves(Node parent) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < parent.child.size(); i++) {
            if (parent.child.get(i).child.size() == 0) {
                arrayList.add(i);
            } else {
                removeLeaves(parent.child.get(i));
            }
        }
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            parent.child.remove(arrayList.get(i) + 0);
        }
    }

    public void preorder(Node parent) {
        System.out.print(parent.value + " ");
        for (int i = 0; i < parent.child.size(); i++)
            preorder(parent.child.get(i));
    }

    public void postorder(Node parent) {
        for (int i = 0; i < parent.child.size(); i++)
            postorder(parent.child.get(i));
        System.out.print(parent.value + " ");
    }

    public void levelOrder() {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        while (!que.isEmpty()) {
            System.out.print(que.getFirst().value + " ");
            for (int i = 0; i < que.getFirst().child.size(); i++) {
                que.addLast((que.getFirst().child.get(i)));
            }
            que.removeFirst();
        }
    }

    public static void main(String[] args) {
        GenericTree genericTree = new GenericTree();
        genericTree.display(genericTree.getRoot());
        genericTree.preorder(genericTree.getRoot());
        System.out.println(" ");
        genericTree.postorder(genericTree.getRoot());
        System.out.println(" ");
        genericTree.levelOrder();
        System.out.println(" ");
        genericTree.removeLeaves(genericTree.getRoot());
        genericTree.levelOrder();
    }
}
