package interview.leetcode._18x;

import java.util.Arrays;

import static competition.utility.Swap.swap;

/**
 * Created by zzt on 11/21/17.
 * <p>
 * <h3></h3>
 */
public class RotateArray {
    private static enum Dir {
        LEFT {
            @Override
            public Dir reverse() {
                return RIGHT;
            }
        }, RIGHT {
            @Override
            public Dir reverse() {
                return LEFT;
            }
        };

        public abstract Dir reverse();
    }

    public void rotate(int[] nums, int k) {
        int l = nums.length;
        Dir dir = Dir.RIGHT;
        if (k >= l) {
            k = k % l;
        }
        if (k > l - k) {
            k = l - k;
            dir = Dir.LEFT;
        }
        rotateDC(nums, 0, l, k, dir);
        rotateReverse(nums, k, dir.reverse());
    }

    private void rotateReverse(int[] n, int k, Dir dir) {
        int mid = k, l = n.length;
        if (dir == Dir.RIGHT) {
            mid = n.length - k;
        }
        for (int i = 0; i < mid / 2; i++) {
            swap(n, i, mid - 1 - i);
        }
        for (int i = mid; i < (l + mid) / 2; i++) {
            swap(n, i, l - 1 + mid - i);
        }
        for (int i = 0; i < l / 2; i++) {
            swap(n, i, l - 1 - i);
        }
    }

    private void rotateDC(int[] n, int s, int e, int k, Dir dir) {
        int l = e - s;
        if (k > l - k) {
            k = l - k;
            dir = dir.reverse();
        }
        if (k==0) {
            return;
        }

        if (l <= 1) {
            return;
        }
        int ms = e - k;
        for (int i = 0; i < k; i++) {
            swap(n, ms + i, s + i);
        }
        if (dir == Dir.LEFT) {
            rotateDC(n, s, ms, k, dir);
        } else {
            rotateDC(n, s + k, e, k, dir);
        }
    }

    private static void test(int[] s, int k) {
        System.out.println(Arrays.toString(s));
        RotateArray r = new RotateArray();
        r.rotate(s, k);
        System.out.println(Arrays.toString(s));
    }

    public static void main(String[] args) {
        //        test(new int[]{1},1);
        //        test(new int[]{1},123);
        test(new int[]{1, 2, 3, 4, 5, 6,}, 2);
        test(new int[]{1, 2, 3, 4, 5, 6,}, 0);
        test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 123);
        test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 23);
        test(new int[]{1, 2, 3}, 23);
        test(new int[]{1, 2, 3}, 21);
        test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 1);
    }
}
