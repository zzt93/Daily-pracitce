package interview.leetcode._2xx._28x;

/**
 * @author zzt
 */
public class DupNum {

  public static void main(String[] args) {
    DupNum d = new DupNum();
    System.out.println(d.findDuplicate(new int[]{8,1,1,1,2,7,4,3,1,1}));
    System.out.println(d.findDuplicate(new int[]{14,16,12,1,16,17,6,8,5,19,16,13,16,3,11,16,4,16,9,7}));
    System.out.println(d.findDuplicate(new int[]{1, 1}));
    System.out.println(d.findDuplicate(new int[]{1, 1, 2, 3, 4, 5, 6}));
    System.out.println(d.findDuplicate(new int[]{1, 1, 2, 3, 1, 5, 6}));
    System.out.println(d.findDuplicate(new int[]{1, 2, 3, 4, 5, 6, 6}));
    System.out.println(d.findDuplicate(new int[]{1, 2, 3, 4, 4, 5, 6}));
    System.out.println(d.findDuplicate(new int[]{1, 4, 2, 3, 4, 6, 5,}));
  }

  public int findDuplicate(int[] nums) {
    int s = 1, e = nums.length - 1;
    int mid;
    while (true) {
      mid = (s + e) / 2;
      int c1 = 0, c2 = 0;
      for (int num : nums) {
        if (num > mid && num <= e) {
          c1++;
        } else if (num == mid) {
          c2++;
        }
      }
      if (c2 > 1) {
        return mid;
      } else if (c1 > e - mid) {
        s = mid+1;
      } else {
        e = mid-1;
      }
    }
  }

}
