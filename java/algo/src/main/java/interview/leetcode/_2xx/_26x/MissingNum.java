package interview.leetcode._2xx._26x;

/**
 * @author zzt
 */
public class MissingNum {


  public int missingNumber(int[] nums) {
    int s = 0;
    for (int num : nums) {
      s += num;
    }
    int l = nums.length;
    int c = l * (l+1)/2;
    return c - s;
  }

}
