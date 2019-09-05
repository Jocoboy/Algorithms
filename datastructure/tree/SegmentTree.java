/**
 * Segment Trees are an extremely useful data structure when dealing with ranges
 * or intervals. They take O(n) time and space to construct, but they can do
 * range updates or queries in O(log(n)) time. This data structure is quite
 * flexible; although the code below supports minimum and sum queries, these
 * could be modified to perform other types of queries. This implementation uses
 * lazy propagation (which allows for O(log(n)) range updates instead of O(n)).
 * It should also be noted that this implementation could easily be modified to
 * support coordinate compression (you should only have to change a few lines in
 * the constructor).
 */

package datastructure.tree;

public class SegmentTree {

  static final int INF = Integer.MAX_VALUE;

  SegmentTree lChild, rChild;
  int minPos, maxPos, min = 0, sum = 0, lazy = 0;

  public SegmentTree(int[] values) {
    if (values == null) {
      throw new IllegalArgumentException("Null input to segment tree.");
    }
    buildTree(0, values.length);
    for (int i = 0; i < values.length; i++) {
      update(i, i + 1, values[i]);
    }
  }

  public SegmentTree(int length) {
    buildTree(0, length);
  }

  private SegmentTree(int l, int r) {
    buildTree(l, r);
  }

  // Recursive method that builds the segment tree
  private void buildTree(int l, int r) {

    if (l < 0 || r < 0 || r < l) {
      throw new IllegalArgumentException("Illegal range: (" + l + "," + r + ")");
    }

    minPos = l;
    maxPos = r;

    // Reached leaf
    if (l == r - 1) {
      lChild = rChild = null;

      // Add children
    } else {
      int mid = (l + r) / 2;
      lChild = new SegmentTree(l, mid);
      rChild = new SegmentTree(mid, r);
    }
  }

  // Adjust all values in the interval [l, r) by a particular amount
  public void update(int l, int r, int change) {

    // Do lazy updates to children
    propagate();

    // SegmentTree's range fits inside query range
    if (l <= minPos && r >= maxPos) {

      sum += change * (maxPos - minPos);
      min += change;

      // Lazily propagate update to children
      if (lChild != null) {
        lChild.lazy += change;
      }
      if (rChild != null) {
        rChild.lazy += change;
      }

      // Ranges do not overlap
    } else if (r <= minPos || l >= maxPos) {

      // Do nothing

      // Ranges partially overlap
    } else {

      if (lChild != null) {
        lChild.update(l, r, change);
      }
      if (rChild != null) {
        rChild.update(l, r, change);
      }
      sum = (lChild == null ? 0 : lChild.sum) + (rChild == null ? 0 : rChild.sum);
      min = Math.min((lChild == null ? INF : lChild.min), (rChild == null ? INF : rChild.min));
    }
  }

  // Get the sum in the interval [l, r)
  public int sum(int l, int r) {

    // Do lazy updates to children
    propagate();

    // SegmentTree's range fits inside query range
    if (l <= minPos && r >= maxPos) {
      return sum;
    }

    // Ranges do not overlap
    else if (r <= minPos || l >= maxPos) {
      return 0;
    }

    // Ranges partially overlap
    return (lChild == null ? 0 : lChild.sum(l, r)) + (rChild == null ? 0 : rChild.sum(l, r));
  }

  // Get the minimum value in the interval [l, r)
  public int min(int l, int r) {

    // Do lazy updates to children
    propagate();

    // SegmentTree's range fits inside query range
    if (l <= minPos && r >= maxPos) {
      return min;
    }

    // Ranges do not overlap
    else if (r <= minPos || l >= maxPos) {
      return INF;
    }

    // Ranges partially overlap
    return Math.min((lChild == null ? INF : lChild.min(l, r)), (rChild == null ? INF : rChild.min(l, r)));
  }

  // Does any updates to this node that haven't been done yet, and lazily updates
  // its children
  // NOTE: This method must be called before updating or accessing a node
  private void propagate() {

    if (lazy != 0) {

      sum += lazy * (maxPos - minPos);
      min += lazy;

      // Lazily propagate updates to children
      if (lChild != null) {
        lChild.lazy += lazy;
      }
      if (rChild != null) {
        rChild.lazy += lazy;
      }

      lazy = 0;
    }
  }

  public static void main(String[] args) {
    int[] values = { 8, 3, 7, 11, 27, 1, 112, 9, 5 };
    SegmentTree segmentTree = new SegmentTree(values);
    System.out.println(segmentTree.min(5, 9));
    System.out.println(segmentTree.sum(0, 9));
  }
}