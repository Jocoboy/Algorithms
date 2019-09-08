package datastructure.tree;

import java.util.Arrays;
import java.util.Scanner;

// successfully passed POJ-2104 (remove package and rename class name as ‘Main’ before submitting)
public class PersistentSegmentTree {

    static class Node {
        Node lChild, rChild;
        int sum;

        public Node(int sum) {
            this.sum = sum;
        }

        public Node(Node lChild, Node rChild) {
            this.lChild = lChild;
            this.rChild = rChild;
            if (lChild != null) {
                this.sum += lChild.sum;
            }
            if (rChild != null) {
                this.sum += rChild.sum;
            }
        }
    }

    static final int N = 100005;
    static int[] unsorted = new int[N];
    static int[] sorted = new int[N];
    static Node[] trees = new Node[N];

    public static Node build(int l, int r) {
        if (l == r) {
            return new Node(0);
        }
        int mid = (l + r) / 2;
        return new Node(build(l, mid), build(mid + 1, r));
    }

    public static Node update(Node root, int pos, int l, int r) {
        if (l == r) {
            return new Node(root.sum + 1);
        }
        int mid = (l + r) / 2;
        if (pos <= mid) {
            return new Node(update(root.lChild, pos, l, mid), root.rChild);
        } else {
            return new Node(root.lChild, update(root.rChild, pos, mid + 1, r));
        }
    }

    public static int query(Node s, Node t, int l, int r, int k) {
        if (l == r) {
            return l;
        }
        int mid = (l + r) / 2;
        int cnt = t.lChild.sum - s.lChild.sum;
        if (k <= cnt) {
            return query(s.lChild, t.lChild, l, mid, k);
        }
        return query(s.rChild, t.rChild, mid + 1, r, k - cnt);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            unsorted[i] = scanner.nextInt();
            sorted[i] = unsorted[i];
        }
        Arrays.sort(sorted, 1, n + 1);
        for (int i = 1; i <= n; i++) {
            unsorted[i] = Arrays.binarySearch(sorted, 1, n + 1, unsorted[i]);
        }
        trees[0] = build(1, n);
        for (int i = 1; i <= n; i++) {
            trees[i] = update(trees[i - 1], unsorted[i], 1, n);
        }
        for (int i = 1; i <= m; i++) {
            int s = scanner.nextInt();
            int t = scanner.nextInt();
            int k = scanner.nextInt();
            System.out.println(sorted[query(trees[s - 1], trees[t], 1, n, k)]);
        }
        scanner.close();
    }
}