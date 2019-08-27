package datastructure.tree;

/**
 * A binary tree is a data structure in which an element has two
 * successors(children). The left child is usually smaller than the parent, and
 * the right child is usually bigger.
 */
public class BinaryTree {

    class Node {

        int value;
        Node lChild;
        Node rChild;
        Node parent;

        public Node(int value) {
            this.value = value;
            lChild = null;
            rChild = null;
            parent = null;
        }
    }

    private Node root;

    public BinaryTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    /**
     * Find a node with a certain value.
     * 
     * @param value - value to be found
     * @return - the found node , otherwise the parent
     */
    public Node find(int value) {
        Node curNode = root;
        while (curNode != null) {
            if (value < curNode.value) {
                if (curNode.lChild == null) {
                    return curNode;
                }
                curNode = curNode.lChild;
            } else if (value > curNode.value) {
                if (curNode.rChild == null) {
                    return curNode;
                }
                curNode = curNode.rChild;
            } else {
                return curNode;
            }
        }
        return null;
    }

    /**
     * This method finds the Successor to the node given. Move right once and go
     * left down the tree as far as you can.
     * 
     * @param node - node that you want to find the Successor of
     * @return - the successor of the node
     */
    public Node findSuccessor(Node node) {
        if (node.rChild == null) {
            return node;
        }
        Node curNode = node.rChild;
        Node parent = node.rChild;
        while (curNode != null) {
            parent = curNode;
            curNode = curNode.lChild;
        }
        return parent;
    }

    /**
     * Insert certain value into the Binary Tree.
     * 
     * @param value - value to be inserted
     */
    public void put(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
        } else {
            Node parent = find(value);
            if (value < parent.value) {
                parent.lChild = newNode;
                parent.lChild.parent = parent;
                return;
            } else {
                parent.rChild = newNode;
                parent.rChild.parent = parent;
                return;
            }
        }
    }

    /**
     * Delete a given value from the Binary Tree.
     * 
     * @param value - value to be deleted
     * @return if successfully deleted
     */
    public boolean remove(int value) {
        Node tempNode = find(value);

        if (tempNode.value != value) {
            return false;
        }

        // If no child.
        if (tempNode.lChild == null && tempNode.rChild == null) {
            if (tempNode == root) {
                root = null;
            } else if (tempNode.value < tempNode.parent.value) {
                tempNode.parent.lChild = null;
            } else {
                tempNode.parent.rChild = null;
            }
            return true;
        }

        // If two children.
        else if (tempNode.lChild != null && tempNode.rChild != null) {
            Node successor = findSuccessor(tempNode);
            // The left tree of tempNode is made the left tree of the successor.
            successor.lChild = tempNode.lChild;
            successor.lChild.parent = successor;

            // If the successor has a right child.
            if (successor.rChild != null && successor.parent != tempNode) {
                // The child's grandparent is its new parent.
                successor.rChild.parent = successor.parent;
                successor.parent.lChild = successor.rChild;
                // Replace tempNode with successor.
                successor.rChild = tempNode.rChild;
                successor.rChild.parent = successor;
            }

            if (tempNode == root) {
                Node sParent = successor.parent;
                sParent.lChild = null;
                successor.parent = null;
                root = successor;
                root.rChild = sParent;
                return true;
            } else {
                successor.parent = tempNode.parent;
                if (tempNode.value < tempNode.parent.value) {
                    tempNode.parent.lChild = successor;
                } else {
                    tempNode.parent.rChild = successor;
                }
                return true;
            }
        }

        // If one child.
        else {
            // If it has a left child.
            if (tempNode.rChild != null) {
                if (tempNode == root) {
                    root = tempNode.rChild;
                    return true;
                }
                tempNode.rChild.parent = tempNode.parent;
                if (tempNode.value < tempNode.parent.value) {
                    tempNode.parent.lChild = tempNode.rChild;
                } else {
                    tempNode.parent.rChild = tempNode.rChild;
                }
                return true;
            } else {
                if (tempNode == root) {
                    root = tempNode.lChild;
                    return true;
                }
                tempNode.lChild.parent = tempNode.parent;
                if (tempNode.value < tempNode.parent.value) {
                    tempNode.parent.lChild = tempNode.lChild;
                } else {
                    tempNode.parent.rChild = tempNode.lChild;
                }
                return true;
            }
        }
    }

    /**
     * Print lChild - root - rChild
     * 
     * @param localRoot - local root of the binary tree
     */
    public void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.lChild);
            System.out.printf("%d ", localRoot.value);
            inOrder(localRoot.rChild);
        }
    }

    /**
     * Print root - lChild - rChild
     * 
     * @param localRoot - local root of the binary tree
     */
    public void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.printf("%d ", localRoot.value);
            preOrder(localRoot.lChild);
            preOrder(localRoot.rChild);
        }
    }

    /**
     * Print lChild - rChild - root
     * 
     * @param localRoot - local root of the binary tree
     */
    public void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.lChild);
            postOrder(localRoot.rChild);
            System.out.printf("%d ", localRoot.value);
        }
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.put(10);
        binaryTree.put(8);
        binaryTree.put(15);
        binaryTree.put(4);
        binaryTree.put(9);
        binaryTree.put(11);
        binaryTree.put(20);
        binaryTree.put(5);

        binaryTree.inOrder(binaryTree.getRoot());// 4 5 8 9 10 11 15 20
        System.out.println(" ");
        binaryTree.preOrder(binaryTree.getRoot());// 10 8 4 5 9 15 11 20
        System.out.println(" ");
        binaryTree.postOrder(binaryTree.getRoot());// 5 4 9 8 11 20 15 10
        System.out.println(" ");

        if (binaryTree.remove(10)) {
            binaryTree.inOrder(binaryTree.getRoot());// 4 5 8 9 11 15 20
        }
    }
}