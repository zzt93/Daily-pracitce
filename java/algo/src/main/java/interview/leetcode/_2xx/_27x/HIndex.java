package interview.leetcode._2xx._27x;

import java.util.Arrays;

/**
 * @author zzt
 */
public class HIndex {

  public static void main(String[] args) {
    HIndex hIndex = new HIndex();
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

  public int hIndex(int[] citations) {
    Arrays.sort(citations);
    int l = citations.length;
    int i, max = 0;
    for (i = l - 1; i >= 0; i--) {
      int c = citations[i];
      if (c == 0) {
        break;
      }
      if (l - i >= c || (l - c >= 0 && citations[l - c] >= c)) {
        max = c;
        break;
      }
    }
    for (int x = 0; x < l; x++) {
      int n = l - x;
      if (citations[x]>=n){
        max = Math.max(n, max);
        break;
      }
    }
    return max;
  }

}
