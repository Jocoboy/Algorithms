package datastructure.tree;

import java.util.Scanner;

public class BinaryIndexedTree {

    private int size;
    // refer to the original array
    private int[] A;
    // refer to the binary indexed tree
    private int[] C;

    // index of array A and C starts from 1
    public BinaryIndexedTree(int size) {
        this.size = size;
        A = new int[size + 1];
        C = new int[size + 1];
    }

    /**
     * 
     * @param x
     * @return 0 if x=0 ; 1 if x is odd ; length of serial zero from low to high
     *         (binary code) if x is even.
     */
    private int lowbit(int x) {
        return x & (-x);
    }

    /**
     * Add or sub.
     * 
     * @param index - index of element to be modified
     * @param value - value after modified
     */
    public void update(int index, int value) {
        while (index <= size) {
            C[index] += value;
            index += lowbit(index);
        }
    }

    /**
     * 
     * @param index - refer to upperbound
     * @return sum among [1,index]
     */
    public int sum(int index) {
        int res = 0;
        while (index > 0) {
            res += C[index];
            index -= lowbit(index);
        }
        return res;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 1; i <= t; i++) {
            System.out.println("Case " + i + ":");
            int n = scanner.nextInt();
            BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(n);
            for (int j = 1; j <= n; j++) {
                binaryIndexedTree.A[j] = scanner.nextInt();
                binaryIndexedTree.update(j, binaryIndexedTree.A[j]);
            }

            while (true) {
                String s = scanner.next();
                if (s.charAt(0) == 'E')
                    break;
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (s.charAt(0) == 'Q') {
                    System.out.println(binaryIndexedTree.sum(y) - binaryIndexedTree.sum(x - 1));
                } else if (s.charAt(0) == 'A') {
                    binaryIndexedTree.update(x, y);
                } else if (s.charAt(0) == 'S') {
                    binaryIndexedTree.update(x, -y);
                }

            }
            scanner.close();
        }
    }
}