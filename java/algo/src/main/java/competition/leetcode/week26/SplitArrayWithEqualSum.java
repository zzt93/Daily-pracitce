package competition.leetcode.week26;

/**
 * Created by zzt on 4/2/17.
 * <p>
 * <h3></h3>
 */
public class SplitArrayWithEqualSum {

    public boolean splitArray(int[] nums) {
        int len = nums.length;
        if (len < 7) {
            return false;
        }
        // sum [0, i)
        int[] l2r = new int[len + 1];
        l2r[0] = 0;
        // sum [i, len)
        int[] r2l = new int[len + 1];
        r2l[len] = 0;
        for (int i = 0; i < nums.length; i++) {
            l2r[i + 1] = nums[i] + l2r[i];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            r2l[i] = r2l[i + 1] + nums[i];
        }

        int i = 1, kPlusOne = len - 1;
        int tmpK = kPlusOne;
        while (i <= kPlusOne - 5) {
            while (i <= tmpK - 5 && l2r[i] != r2l[tmpK]) {
                tmpK--;
            }
            if (i > tmpK - 5) {
                i++;
                tmpK = kPlusOne;
            } else if (l2r[i] == r2l[tmpK]) {
                if (existsK(l2r, r2l, i, tmpK - 1)) {
                    System.out.printf("i: %d; k: %d\n", i, tmpK - 1);
                    return true;
                } else {
                    tmpK--;
                }
            }
        }
        return false;
    }

    private boolean existsK(int[] l2r, int[] r2l, int i, int k) {
        for (int j = i + 2; j < k - 1; j++) {
            if (lsum(i, j, l2r) == rsum(j, k, r2l)) {
                System.out.printf("j: %d;", j);
                return true;
            }
        }
        return false;
    }

    private int lsum(int i, int j, int[] l2r) {
        return l2r[j] - l2r[i + 1];
    }

    private int rsum(int j, int k, int[] r2l) {
        return r2l[j + 1] - r2l[k];
    }


    public static void main(String[] args) {
        SplitArrayWithEqualSum sum = new SplitArrayWithEqualSum();
        System.out.println(sum.splitArray(new int[]{2, 4, 3, 10, 3, 2, 4, 4, 9, 3, 5, 8, 8, 9, 3,
                0, 4, 3, 3, 9, 4, 3, 4, 1, 9, 7, 2, 9, 1, 9, 10, 5, 5, 5, 5, 3, 3, 10, 9, 3, 7,
                6, 6, 2, 7, 7, 9, 8, 3, 7, 2, 4, 4, 9, 4, 5, 10, 7, -1, 0, 5, 1, 9, 4, 2, 3, 0,
                5, 0, 2, 8, 1, 0, 7, 10, 4, 8, 3, 6, 0, 4, 3, 3, 8, 4}));
        System.out.println(sum.splitArray(new int[]{-1, 2, 2, 1, 3, -2, 3, 4, 1}));
        System.out.println(sum.splitArray(new int[]{1, 2, 1, 2, 1, 2, 1, 2}));
        System.out.println(sum.splitArray(new int[]{0, 0, 0, 0, 0, 0, 0}));
    }
}
