package interview.leetcode._3xx._30x;

import ADT.tree.SegmentTree.SegmentTree;
import java.util.Arrays;

/**
 * @author zzt
 */
public class NumArray2 {

  private final SegmentTree segmentTree;

  public NumArray2(int[] nums) {
    segmentTree = new SegmentTree(nums,
        (a, b) -> a == null ? b == null ? 0 : b : b == null ? a : a + b, (old, ne)->ne-old);
  }

  public void update(int i, int val) {
    segmentTree.update(i, val);
  }

  public int sumRange(int i, int j) {
    return segmentTree.query(i, j+1);
  }

  private static void test(int... a) {
    NumArray2 n = new NumArray2(a);
    System.out.println("---------: " + Arrays.toString(a));
    System.out.println(n.sumRange(0, a.length-1));
    System.out.println(n.sumRange(0, a.length/2));
    System.out.println(n.sumRange(a.length/2, a.length-1));
    System.out.println(n.sumRange(0, a.length/3));
    System.out.println(n.sumRange(a.length/3, a.length-1));
    System.out.println(n.sumRange(0, a.length/4));
    System.out.println(n.sumRange(a.length/4, a.length-1));
    n.update(1, 7);
    System.out.println("--------: " + Arrays.toString(a));
    System.out.println(n.sumRange(0, a.length-1));
    System.out.println(n.sumRange(0, a.length/2));
    System.out.println(n.sumRange(a.length/2, a.length-1));
    System.out.println(n.sumRange(0, a.length/3));
    System.out.println(n.sumRange(a.length/3, a.length-1));
    System.out.println(n.sumRange(0, a.length/4));
    System.out.println(n.sumRange(a.length/4, a.length-1));
  }
  public static void main(String[] args) {
//    test();
    test(0,9,5,7,3);
    test(-28,-39,53,65,11,-56,-65,-39,-43,97);
    test(1);
    test(1,2);
    test(1,2,3);
    test(1,2,3,4);
  }
}
