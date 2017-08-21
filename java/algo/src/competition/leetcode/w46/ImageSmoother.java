package competition.leetcode.w46;

/**
 * Created by zzt on 8/20/17.
 * <p>
 * <h3></h3>
 */
public class ImageSmoother {

    public int[][] imageSmoother(int[][] m) {
        int[][] res = new int[m.length][m[0].length];
        for (int r = 0; r < res.length; r++) {
            int[] re = res[r];
            for (int c = 0; c < re.length; c++) {
                re[c] = ave(m, r, c);
            }
        }
        return res;
    }

    private int ave(int[][] m, int r, int c) {
        int rl = m.length;
        int cl = m[0].length;
        int sum = m[r][c], count = 1;
        if (r != 0) {
            count++;
            sum += m[r - 1][c];
            if (c != 0) {
                sum += m[r - 1][c - 1];
                count++;
            }
            if (c != cl - 1) {
                sum += m[r - 1][c + 1];
                count++;
            }
        }
        if (r != rl - 1) {
            count++;
            sum += m[r + 1][c];
            if (c != 0) {
                sum += m[r + 1][c - 1];
                count++;
            }
            if (c != cl - 1) {
                sum += m[r + 1][c + 1];
                count++;
            }
        }
        if (c != 0) {
            count++;
            sum += m[r][c - 1];
        }
        if (c != cl - 1) {
            count++;
            sum += m[r][c + 1];
        }
        return sum / count;
    }
}
