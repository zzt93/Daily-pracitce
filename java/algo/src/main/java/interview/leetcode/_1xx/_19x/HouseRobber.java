package interview.leetcode._1xx._19x;

/**
 * Created by zzt on 12/4/17.
 * <p>
 * <h3></h3>
 */
public class HouseRobber {

    public int rob(int[] n) {
        int l = n.length;
        if (l == 0) {
            return 0;
        } else if (l == 1) {
            return n[0];
        } else if (l == 2) {
            return Math.max(n[0], n[1]);
        } else if (l == 3) {
            return Math.max(n[0] + n[2], n[1]);
        }
        int[] max = new int[l];
        max[0] = n[0];
        max[1] = n[1];
        max[2] = n[0] + n[2];
        for (int i = 3; i < l; i++) {
            max[i] = Math.max(max[i - 2], max[i - 3]) + n[i];
        }
        return Math.max(max[l - 1], max[l - 2]);
    }

    public static void main(String[] args) {
        HouseRobber h = new HouseRobber();
        System.out.println(h.rob(new int[]{}));
        System.out.println(h.rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println(h.rob(new int[]{1}));
        System.out.println(h.rob(new int[]{1, 2}));
        System.out.println(h.rob(new int[]{1, 3, 1, 1, 2}));
    }
}
