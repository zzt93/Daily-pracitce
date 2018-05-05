package interview.leetcode._3xx._30x;

import java.util.Arrays;

/**
 * @author zzt
 */
public class LongestIncreasingSubsequence {

  public static void main(String[] args) {
    LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
    System.out.println(l.lengthOfLIS2(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
  }

  public int lengthOfLIS(int[] nums) {
    int l = nums.length;
    if (l == 0) {
      return 0;
    }
    int[] dp = new int[l];
    dp[0] = 1;
    for (int i = 1; i < l; i++) {
      for (int j = 0; j < i; j++) {
        dp[i] = Math.max(dp[i], nums[i] > nums[j] ? dp[j] + 1 : 1);
      }
    }
    return Arrays.stream(dp).max().getAsInt();
  }

  public int lengthOfLIS2(int[] nums) {
    int[] lisEnd = new int[nums.length];
    int size = 0;
    for (int n : nums) {
      int i = Arrays.binarySearch(lisEnd, 0, size, n);
      if (i < 0) {
        i = -(i + 1);
      }
      lisEnd[i] = n;
      if (i == size) size++;
    }
    return size;
  }

}
