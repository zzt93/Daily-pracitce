package interview.leetcode._2xx._28x;

import java.util.Arrays;

/**
 * @author zzt
 */
public class MoveZeros {

  public void moveZeroes(int[] nums) {
    int j = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        nums[j++] = nums[i];
      }
    }
    for (int i = j; i < nums.length; i++) {
      nums[i]=0;
    }
  }

  public static void main(String[] args) {
    MoveZeros m = new MoveZeros();
    int[] nums = {0, 1, 0, 3, 12};
    m.moveZeroes(nums);
    System.out.println(Arrays.toString(nums));
    nums = new int[]{0,0,0,0};
    m.moveZeroes(nums);
    System.out.println(Arrays.toString(nums));
    nums = new int[]{1,2,3,4,5};
    m.moveZeroes(nums);
    System.out.println(Arrays.toString(nums));
  }

}
