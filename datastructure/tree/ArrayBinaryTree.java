
public class ArrayBinaryTree {

    private int[] biTree;

    public ArrayBinaryTree(int len) {
        biTree = new int[len];
        for (int i = 0; i < len; i++) {
            biTree[i] = -1;
        }
    }

    public void insertBiTNode(int data) {
        if (biTree[0] == -1) {
            biTree[0] = data;
            return;
        }
        int ind = 0;
        while (true) {
            if (ind > biTree.length - 1) {
                throw new IndexOutOfBoundsException("BiTree is full.");
            }
            if (biTree[ind * 2 + 1] == -1) {
                biTree[ind * 2 + 1] = data;
                break;
            } else if (biTree[ind * 2 + 2] == -1) {
                biTree[ind * 2 + 2] = data;
                break;
            } else {
                ind = ind + 1;
            }
        }

    }

    public void levelOrder() {
        for (int i = 0; i < biTree.length; i++) {
            if (biTree[i] != -1) {
                System.out.printf(biTree[i] + " ");
            }
        }
    }

    public static void main(String[] args) {
        ArrayBinaryTree aBiTree = new ArrayBinaryTree(100);
        aBiTree.insertBiTNode(8);
        aBiTree.insertBiTNode(3);
        aBiTree.insertBiTNode(19);
        aBiTree.insertBiTNode(1);
        aBiTree.insertBiTNode(6);
        aBiTree.insertBiTNode(14);
        aBiTree.insertBiTNode(-1);
        aBiTree.insertBiTNode(-1);
        aBiTree.insertBiTNode(-1);
        aBiTree.insertBiTNode(4);
        aBiTree.insertBiTNode(7);
        aBiTree.insertBiTNode(13);

        System.out.printf("levelOrder: ");
        aBiTree.levelOrder();
        System.out.println();
    }

}