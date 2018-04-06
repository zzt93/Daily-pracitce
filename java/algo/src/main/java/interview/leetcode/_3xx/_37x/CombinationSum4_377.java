package interview.leetcode._3xx._37x;

/**
 * @author zzt
 */
public class CombinationSum4_377 {

  public int combinationSum4(int[] nums, int target) {
    int[] dp = new int[target+1];
    for (int n: nums) {
      if (n <= target) dp[n] = 1;
    }
    for (int i = 1; i <= target; i++) {
      for (int n: nums) {
        if (i > n) dp[i] += dp[i-n];
      }
    }
    return dp[target];
  }

  public static void main(String[] args) {
    CombinationSum4_377 c = new CombinationSum4_377();
    System.out.println(c.combinationSum4(new int[]{}, 7));
    System.out.println(c.combinationSum4(new int[]{1}, 7));
    System.out.println(c.combinationSum4(new int[]{1,7}, 7));
    System.out.println(c.combinationSum4(new int[]{1,11}, 7));
  }

}
