package competition.leetcode.w48;

/**
 * Created by zzt on 9/3/17.
 * <p>
 * <h3></h3>
 */
@Deprecated
public class BulbSwitcher2 {

    private int[][] ints = {
            new int[]{1, 2, 4, 8, 10}, // 4 * n, n >= 1
            new int[]{1, 2, 3, 4},
            new int[]{1, 2, 4, 7},
            new int[]{1, 2, 4, 8},
    };

    public int flipLights(int n, int m) {
        int r;
        if (m == 0) {
            return 1;
        } else {
            r = m % 4;
        }
        int len = ints[r].length;
        if (n >= len) {
            return ints[r][len - 1];
        }
        return ints[r][n];
    }

    public static void main(String[] args) {
        BulbSwitcher2 switcher2 = new BulbSwitcher2();
        System.out.println(switcher2.flipLights(3, 5));
    }
}
