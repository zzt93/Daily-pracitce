package competition.leetcode.w48;

/**
 * Created by zzt on 9/3/17.
 * <p>
 * <h3></h3>
 */
public class BulbSwitcher2 {

    private int[][] ints = {
            // k >= 1
            new int[]{1, 2, 3, 4}, // m = 4 * k - 3
            new int[]{1, 2, 4, 7}, // m = 4 * k - 2
            new int[]{1, 2, 4, 8}, // m = 4 * k - 1
            new int[]{1, 2, 4, 8}, // m = 4 * k
    };

    public int flipLights(int n, int m) {
        int r;
        if (m == 0) {
            return 1;
        } else if (m > 4){
            // either same with (4 * k - 1), or (4 * k)
            r = (m % 2 == 0 ? 3 : 2);
        } else {
            r = m - 1;
        }
        int len = ints[r].length;
        if (n >= len) {
            return ints[r][len - 1];
        }
        return ints[r][n];
    }

    public static void main(String[] args) {
        BulbSwitcher2 switcher2 = new BulbSwitcher2();
        System.out.println(switcher2.flipLights(1, 1));
        System.out.println(switcher2.flipLights(3, 5));
        System.out.println(switcher2.flipLights(3, 2));
        System.out.println(switcher2.flipLights(3, 4));
        System.out.println(switcher2.flipLights(100, 99));
        System.out.println(switcher2.flipLights(10, 40));
    }
}
