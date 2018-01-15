package interview.leetcode._2xx._27x;

/**
 * @author zzt
 */
public class HIndex2 {

  public static void main(String[] args) {
    HIndex2 hIndex = new HIndex2();
    System.out.println(hIndex.hIndex(new int[]{1, 7, 9, 4}));
    System.out.println(hIndex.hIndex(new int[]{}));
    System.out.println(hIndex.hIndex(new int[]{4, 4, 0, 0}));
    System.out.println(hIndex.hIndex(new int[]{100}));
    System.out.println(hIndex.hIndex(new int[]{100, 99}));
    System.out.println(hIndex.hIndex(new int[]{100, 99, 98, 4}));
    System.out.println(hIndex.hIndex(new int[]{1, 2, 3, 3, 3}));
    System.out.println(hIndex.hIndex(new int[]{3, 0, 6, 1, 5}));
    System.out.println(hIndex.hIndex(new int[]{1, 2, 3, 2, 2}));
  }

  private int max = 0;

  public int hIndex(int[] citations) {
    max = 0;
    recur(citations, 0, citations.length);
    return max;
  }

  private void recur(int[] n, int s, int e) {
    if (s >= e) {
      return;
    }
    int l = n.length;
    int mid = (s + e) / 2;
    if (n[mid] > l - mid) {
      max = Math.max(max, l - mid);
      recur(n, s, mid);
    } else if (n[mid] < l - mid) {
      max = Math.max(max, n[mid]);
      recur(n, mid + 1, e);
    } else {
      max = Math.max(max, l - mid);
    }
  }

}
