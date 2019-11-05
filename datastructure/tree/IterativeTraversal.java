package datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class IterativeTraversal {

    class BiTNode {
        int data;
        BiTNode lChild;
        BiTNode rChild;

        public BiTNode(int data) {
            this.data = data;
            this.lChild = null;
            this.rChild = null;
        }
    }

    private BiTNode root;

    public IterativeTraversal() {
        root = null;
    }

    public BiTNode getRoot() {
        return root;
    }

    public BiTNode findNode(int data) {
        BiTNode curNode = root;
        while (curNode != null) {
            if (data < curNode.data) {
                if (curNode.lChild == null) {
                    return curNode;
                }
                curNode = curNode.lChild;
            } else if (data > curNode.data) {
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

    public void InsertChild(int data) {
        BiTNode newNode = new BiTNode(data);
        if (root == null) {
            root = newNode;
        } else {
            BiTNode pareNode = findNode(data);
            if (data < pareNode.data) {
                pareNode.lChild = newNode;
            } else {
                pareNode.rChild = newNode;
            }
        }
    }

    /**
     * Traversal order: root - lChild - rChild
     * 
     * @param localRoot - root of the subtree
     */
    public void preOrder(BiTNode localRoot) {
        if (localRoot != null) {
            System.out.printf("%d ", localRoot.data);
            preOrder(localRoot.lChild);
            preOrder(localRoot.rChild);
        }
    }

    /**
     * Iterative version of preOrder traversal.
     * 
     * @param localRoot - root of the subtree
     */
    public void preOrder_iterative_version(BiTNode localRoot) {
        if (localRoot == null) {
            return;
        }
        Stack<BiTNode> stk = new Stack<BiTNode>();
        List<Integer> lst = new ArrayList<>();
        stk.add(localRoot);
        while (!stk.isEmpty()) {
            BiTNode curNode = stk.pop();
            lst.add(curNode.data);
            if (curNode.rChild != null) {
                stk.push(curNode.rChild);
            }
            if (curNode.lChild != null) {
                stk.push(curNode.lChild);
            }
        }
        for (Integer data : lst) {
            System.out.printf("%d ", data);
        }
    }

    /**
     * Traversal order: lChild - root - rChild
     * 
     * @param localRoot - root of the subtree
     */
    public void inOrder(BiTNode localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.lChild);
            System.out.printf("%d ", localRoot.data);
            inOrder(localRoot.rChild);
        }
    }

    /**
     * Iterative version of inOrder traversal.
     * 
     * @param localRoot - root of the subtree
     */
    public void inOrder_iterative_version(BiTNode localRoot) {
        if (localRoot == null) {
            return;
        }
        Stack<BiTNode> stk = new Stack<BiTNode>();
        List<Integer> lst = new ArrayList<>();
        BiTNode curNode = localRoot;
        while (!stk.isEmpty() || curNode != null) {
            // Search for the far left childNode to be the first node.
            while (curNode != null) {
                stk.add(curNode);
                curNode = curNode.lChild;
            }
            // Judge each node in the stack.
            if (!stk.isEmpty()) {
                BiTNode tempNode = stk.pop();
                lst.add((tempNode.data));
                // If curNode is inner node, traverse its rChild node inOrder.
                curNode = tempNode.rChild;
            }
        }
        for (Integer data : lst) {
            System.out.printf("%d ", data);
        }
    }

    /**
     * Traversal order: lChild - rChild - root
     * 
     * @param localRoot - root of the subtree
     */
    public void postOrder(BiTNode localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.lChild);
            postOrder(localRoot.rChild);
            System.out.printf("%d ", localRoot.data);
        }
    }

    /**
     * Iterative version of postOrder traversal.
     * Similar to preOrder traversal.
     * 
     * @param localRoot - root of the subtree
     */
    public void postOrder_iterative_version(BiTNode localRoot) {
        if (localRoot == null) {
            return;
        }
        Stack<BiTNode> stk = new Stack<BiTNode>();
        // Use LinkedList(Deque) to reverse.
        LinkedList<Integer> lst = new LinkedList<Integer>();
        stk.add(localRoot);
        while (!stk.isEmpty()) {
            BiTNode curNode = stk.pop();
            lst.addFirst(curNode.data);
            if (curNode.lChild != null) {
                stk.push(curNode.lChild);
            }
            if (curNode.rChild != null) {
                stk.push(curNode.rChild);
            }
        }
        for (Integer data : lst) {
            System.out.printf("%d ", data);
        }
    }

    public void levelOrder() {
        LinkedList<BiTNode> que = new LinkedList<>();
        que.addLast(root);
        while (!que.isEmpty()) {
            System.out.print(que.getFirst().data + " ");
            if (que.getFirst().lChild != null) {
                que.addLast((que.getFirst().lChild));
            }
            if (que.getFirst().rChild != null) {
                que.addLast((que.getFirst().rChild));
            }
            que.removeFirst();
        }
    }

    public int getHeight(BiTNode localRoot) {
        if (localRoot == null) {
            return 0;
        }
        return Math.max(getHeight(localRoot.lChild), getHeight(localRoot.rChild)) + 1;
    }

    public static void main(String[] args) {
        IterativeTraversal biTree = new IterativeTraversal();
        biTree.InsertChild(8);
        biTree.InsertChild(3);
        biTree.InsertChild(19);
        biTree.InsertChild(1);
        biTree.InsertChild(6);
        biTree.InsertChild(4);
        biTree.InsertChild(7);
        biTree.InsertChild(14);
        biTree.InsertChild(13);

        System.out.printf("preOrder: ");
        biTree.preOrder(biTree.getRoot());
        System.out.println();

        System.out.printf("preOrder_iterative_version: ");
        biTree.preOrder_iterative_version(biTree.getRoot());
        System.out.println();

        System.out.printf("inOrder: ");
        biTree.inOrder(biTree.getRoot());
        System.out.println();

        System.out.printf("inOrder_iterative_version: ");
        biTree.inOrder_iterative_version(biTree.getRoot());
        System.out.println();

        System.out.printf("postOrder: ");
        biTree.postOrder(biTree.getRoot());
        System.out.println();

        System.out.printf("postOrder_iterative_version: ");
        biTree.postOrder_iterative_version(biTree.getRoot());
        System.out.println();

        System.out.printf("levelOrder: ");
        biTree.levelOrder();
        System.out.println();

        System.out.println("Height: " + biTree.getHeight(biTree.getRoot()));
    }
}