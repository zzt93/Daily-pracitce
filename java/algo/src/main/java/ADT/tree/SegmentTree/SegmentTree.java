package ADT.tree.SegmentTree;

import com.google.common.base.Preconditions;
import java.util.function.BiFunction;

/**
 * Created by zzt on 11/19/17. <p> <h3>Query content in a range</h3>
 */
public class SegmentTree {

  private final int len;
  private BiFunction<Integer, Integer, Integer> unionF;
  private int[] array;
  private TNode[] nodes;
  private BiFunction<Integer, Integer, Integer> updateF;

  public SegmentTree(int[] array, BiFunction<Integer, Integer, Integer> unionF,
      BiFunction<Integer, Integer, Integer> updateF) {
    len = array.length;
    if (len == 0) {
      return;
    }
    this.array = array;
    int x = (int) (Math.log(len) / Math.log(2) + 1);
    int max_size = 2 * (int) Math.pow(2, x);
    nodes = new TNode[max_size];
    makeTree(array, 0, array.length, unionF, 1);
    this.unionF = unionF;
    this.updateF = updateF;
  }


  private void makeTree(int[] array, int s, int e,
      BiFunction<Integer, Integer, Integer> unionF, int i) {
    if (s + 1 == e) {
      nodes[i] = new TNode(s, e, array[s]);
    } else {
      int mid = (s + e + 1) / 2;
      makeTree(array, s, mid, unionF, i * 2);
      makeTree(array, mid, e, unionF, i * 2 + 1);
      nodes[i] = new TNode(s, e, unionF.apply(nodes[i * 2].union, nodes[i * 2 + 1].union));
    }
  }

  public int query(int s, int e) {
    Preconditions.checkArgument(s >= 0 && e <= len);
    return query(s, e, 1);
  }

  private int query(int s, int e, int i) {
    Preconditions.checkArgument(s >= nodes[i].l && e <= nodes[i].r);
    if (s == nodes[i].l && e == nodes[i].r) {// leaf node or matched range
      return nodes[i].union;
    }
    Integer r = null, l = null;
    int mid = (nodes[i].l + nodes[i].r + 1) / 2;
    if (s >= mid) {
      r = query(s, e, i * 2 + 1);
    } else if (e <= mid) {
      l = query(s, e, i * 2);
    } else {
      l = query(s, mid, i * 2);
      r = query(mid, e, i * 2 + 1);
    }
    return unionF.apply(r, l);
  }

  public void update(int i, int val) {
    Preconditions.checkArgument(i >= 0 && i < len);
    recur(1, i, updateF.apply(array[i], val));
    array[i] = val;
  }

  private void recur(int i, int arrayI, int update) {
    Preconditions.checkArgument(arrayI >= nodes[i].l && arrayI < nodes[i].r);
    if (nodes[i].l + 1 == nodes[i].r) { // leaf node, base case
      nodes[i].union = unionF.apply(nodes[i].union, update);
      return;
    }
    int mid = (nodes[i].l + nodes[i].r + 1) / 2;
    if (arrayI < mid) {
      recur(i * 2, arrayI, update);
    } else if (arrayI >= mid) {
      recur(i * 2 + 1, arrayI, update);
    }
    nodes[i].union = unionF.apply(nodes[i].union, update);
  }

  private class TNode {

    int l;
    int r;
    int union;

    TNode(int s, int e, int i) {
      l = s;
      r = e;
      union = i;
    }
  }
}
