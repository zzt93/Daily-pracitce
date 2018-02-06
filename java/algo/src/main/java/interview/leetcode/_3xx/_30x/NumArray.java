package interview.leetcode._3xx._30x;

/**
 * @author zzt
 */
public class NumArray {

  private final int[] sum;

  public NumArray(int[] nums) {
    sum = new int[nums.length];
    if (nums.length == 0) {
      return;
    }
    sum[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      sum[i] = sum[i - 1] + nums[i];
    }
  }

  public int sumRange(int i, int j) {
    if (i == 0) {
      return sum[j];
    } else {
      return sum[j] - sum[i - 1];
    }
  }

}
