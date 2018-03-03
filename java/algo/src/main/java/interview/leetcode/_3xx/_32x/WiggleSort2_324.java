package interview.leetcode._3xx._32x;

import java.util.Arrays;

/**
 * @author zzt
 */
public class WiggleSort2_324 {

  private static void test(int... ints) {
    WiggleSort2_324 w = new WiggleSort2_324();
    w.wiggleSort(ints);
    System.out.println(Arrays.toString(ints));
  }

  public static void main(String[] args) {
    test(4, 5, 5, 6);
    test(1, 2, 5, 5, 5, 6);
    test(1, 2, 5, 5, 5, 6, 7);
    test(1, 3, 2, 2, 3, 1, 4);
    test(1, 5, 1, 1, 6, 4);
    test(1, 3, 2, 2, 3, 1);
  }

  public void wiggleSort(int[] nums) {
    Arrays.sort(nums);
    int pre = (nums.length + 1) / 2;
    int left = nums.length / 2;
    int[] tmp = new int[nums.length];
    for (int c = 0, i = pre - 1, j = 0; c < left; c++) {
      tmp[j++] = nums[i];
      tmp[j++] = nums[i-- + left];
    }
    if (left * 2 < nums.length) {
      tmp[nums.length - 1] = nums[0];
    }
    System.arraycopy(tmp, 0, nums, 0, nums.length);
  }

  /**
   * O(n) time and/or in-place with O(1) extra space
   */
  public void followUp(int[] nums) {

  }
}
