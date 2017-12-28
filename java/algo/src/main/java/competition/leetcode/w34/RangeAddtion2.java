package competition.leetcode.w34;

/**
 * Created by zzt on 5/28/17.
 * <p>
 * <h3></h3>
 */
public class RangeAddtion2 {

    public int maxCount(int m, int n, int[][] ops) {
        int xm = m, ym = n;
        for (int[] op : ops) {
            if (xm > op[0]) {
                xm = op[0];
            }
            if (ym > op[1]) {
                ym = op[1];
            }
        }
        return xm * ym;
    }
}
