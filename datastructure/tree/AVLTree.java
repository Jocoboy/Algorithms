package datastructure.tree;

public class AVLTree {

    class Node {
        int key;
        int balance;
        int height;
        Node lChild, rChild, parent;

        Node(int key, Node parent) {
            this.key = key;
            this.parent = parent;
        }
    }

    private Node root;

    private int getHeight(Node n) {
        if (n == null) {
            return -1;
        }
        return n.height;
    }

    private void updateHeight(Node n) {
        if (n != null) {
            n.height = Math.max(getHeight(n.lChild), getHeight(n.rChild));
        }
    }

    private void setBalance(Node... nodes) {
        for (Node n : nodes) {
            updateHeight(n);
            n.balance = getHeight(n.rChild) - getHeight(n.lChild);
        }
    }

    private Node rotateLeft(Node a) {
        Node b = a.rChild;
        b.parent = a.parent;

        a.rChild = b.lChild;

        if (a.rChild != null) {
            a.rChild.parent = a;
        }

        a.lChild = a;
        b.parent = b;

        if (b.parent != null) {
            if (b.parent.rChild == a) {
                b.parent.rChild = b;
            } else {
                b.parent.lChild = b;
            }
        }

        setBalance(a, b);
        return b;
    }

    private Node rotateRight(Node a) {

        Node b = a.lChild;
        b.parent = a.parent;

        a.lChild = b.rChild;

        if (a.lChild != null) {
            a.lChild.parent = a;
        }

        b.rChild = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.rChild == a) {
                b.parent.rChild = b;
            } else {
                b.parent.lChild = b;
            }
        }

        setBalance(a, b);
        return b;
    }

    private Node rotateLeftThenRight(Node n) {
        n.lChild = rotateLeft(n.lChild);
        return rotateRight(n);
    }

    private Node rotateRightThenLeft(Node n) {
        n.rChild = rotateRight(n.rChild);
        return rotateLeft(n);
    }

    private void rebalance(Node node) {
        setBalance(node);

        if (node.balance == -2) {
            if (getHeight(node.lChild.lChild) >= getHeight(node.lChild.rChild)) {
                node = rotateRight(node);
            } else {
                node = rotateLeftThenRight(node);
            }
        } else if (node.balance == 2) {
            if (getHeight(node.rChild.rChild) >= getHeight(node.rChild.lChild)) {
                node = rotateLeft(node);
            } else {
                node = rotateRightThenLeft(node);
            }
        }

        if (node.parent != null) {
            rebalance(node.parent);
        } else {
            root = node;
        }
    }

    public boolean insert(int key) {
        if (root == null) {
            root = new Node(key, null);
        } else {
            Node node = root;
            Node parent;
            while (true) {
                if (node.key == key) {
                    return false;
                }
                parent = node;
                boolean goLeft = key < node.key;
                node = goLeft ? node.lChild : node.rChild;
                if (node == null) {
                    if (goLeft) {
                        parent.lChild = new Node(key, parent);
                    } else {
                        parent.rChild = new Node(key, parent);
                    }
                    rebalance(parent);
                    break;
                }
            }
        }
        return true;
    }

    private void delete(Node node) {
        // Auto Garbage Collection.
        if (node.lChild == null && node.rChild == null) {
            if (node.parent == null) {
                root = null;
            } else {
                Node parent = node.parent;
                if (parent.lChild == node) {
                    parent.lChild = null;
                } else {
                    parent.rChild = null;
                }
                rebalance(parent);
            }
            return;
        }
        // Here similar to normal binary tree.
        if (node.lChild != null) {
            Node child = node.lChild;
            while (child.rChild != null) {
                child = child.rChild;
            }
            node.key = child.key;
            delete(child);
        } else {
            Node child = node.rChild;
            while (child.lChild != null) {
                child = child.lChild;
            }
            node.key = child.key;
            delete(child);
        }
    }

    public void delete(int key) {
        if (root == null) {
            return;
        }
        Node node = root;
        Node child = root;

        // Iterate to find node to be deleted.
        while (child != null) {
            node = child;
            child = (key < node.key ? node.lChild : node.rChild);
            if (key == node.key) {
                delete(node);
                return;
            }
        }
    }

    /**
     * Inorder traversal.
     * 
     * @param node - current node
     */
    private void printBalance(Node node) {
        if (node != null) {
            printBalance(node.lChild);
            System.out.printf("%s ", node.balance);
            printBalance(node.rChild);
        }
    }

    public void printBalance() {
        printBalance(root);
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        for (int i = 1; i < 10; i++) {
            tree.insert(i);
        }
        tree.printBalance();
    }
}