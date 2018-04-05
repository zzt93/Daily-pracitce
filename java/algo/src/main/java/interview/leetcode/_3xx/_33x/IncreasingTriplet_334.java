package interview.leetcode._3xx._33x;

/**
 * @author zzt
 */
public class IncreasingTriplet_334 {

  public boolean increasingTriplet(int[] nums) {
    if (nums.length < 3) return false;
    int minOne = nums[0], minTwo = Integer.MAX_VALUE;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > minTwo) return true;
      else if (nums[i] < minOne) minOne = nums[i];
      else if (nums[i] > minOne && nums[i] < minTwo) minTwo = nums[i];
    }
    return false;
  }

}
