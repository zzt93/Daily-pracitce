package interview.leetcode._3xx._36x;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zzt
 */
public class LargestDivisibleSet_368 {

  public static void main(String[] args) {
    LargestDivisibleSet_368 v = new LargestDivisibleSet_368();
    System.out.println(v.largestDivisibleSubset(new int[]{}));
    System.out.println(v.largestDivisibleSubset(new int[]{1}));
    System.out.println(v.largestDivisibleSubset(new int[]{1, 2, 3}));
    System.out.println(v.largestDivisibleSubset(new int[]{1, 3, 4, 12, 16, 15, 30}));
    System.out.println(v.largestDivisibleSubset(new int[]{1, 2, 4, 6}));
    System.out.println(v.largestDivisibleSubset(new int[]{1, 2, 4, 8}));
    System.out.println(v.largestDivisibleSubset(new int[]{1, 2, 4, 6, 12, 24}));
  }

  public List<Integer> largestDivisibleSubset(int[] nums) {
    if (nums.length ==0) return Collections.emptyList();
    Arrays.sort(nums);
    int l = nums.length;
    int[] dp = new int[l];
    int[] pre = new int[l];
    dp[0] = 1; pre[0] = -1;
    int max = 1, mi = 0;
    for (int i = 1; i < l; i++) {
      boolean in = false;
      for (int x = 0; x < i; x++) {
        if (nums[i] % nums[x] == 0 && dp[i] < dp[x] + 1) {
          in = true;
          dp[i] = dp[x]+1;
          pre[i] = x;
        }
      }
      if (!in) {
        dp[i] = 1;
        pre[i] = -1;
      }
      if (max < dp[i]) {
        max = dp[i];
        mi = i;
      }
    }
    List<Integer> res = new LinkedList<>();
    for (int i = mi; i >= 0; i=pre[i]) {
      res.add(nums[i]);
    }
    return res;
  }


}
