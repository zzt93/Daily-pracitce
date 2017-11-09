package competition.leetcode.w57;

import java.util.Arrays;

/**
 * Created by zzt on 11/8/17.
 * <p>
 * <h3></h3>
 */
public class CandyCrush {

    public int[][] candyCrush(int[][] board) {
        boolean change = true;
        while (change) {
            change = reduce(board);
        }
        return board;
    }

    private boolean reduce(int[][] b) {
        boolean res = false;
        int m = b.length;
        int n = b[0].length;
        boolean[][] zero = new boolean[m][n];
        for (int i = 0; i < b.length; i++) {
            int[] r = b[i];
            for (int x = 0; x + 2 < n; x++) {
                if (r[x] > 0 && r[x] == r[x + 1] && r[x + 1] == r[x + 2]) {
                    zero[i][x] = zero[i][x + 1] = zero[i][x + 2] = true;
                    res = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int x = 0; x + 2 < m; x++) {
                if (b[x][i] > 0 && b[x][i] == b[x + 1][i] && b[x + 1][i] == b[x + 2][i]) {
                    zero[x][i] = zero[x + 1][i] = zero[x + 2][i] = true;
                    res = true;
                }
            }
        }
        if (!res) {
            return false;
        }
        // drop
        for (int i = 0; i < n; i++) {
            int c = m - 1;
            for (int j = m - 1; j >= 0; j--) {
                if (!zero[j][i]) {
                    b[c][i] = b[j][i];
                    c--;
                }
            }
            while (c >= 0) {
                b[c][i] = 0;
                c--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CandyCrush c = new CandyCrush();
        System.out.println(Arrays.deepToString(c.candyCrush(new int[][]{new int[]{1, 3, 5, 5, 2},
                new int[]{3, 4, 3,
                3, 1}, new int[]{3, 2, 4, 5, 2}, new int[]{2, 4, 4, 5, 5}, new int[]{1, 4, 4, 1,
                1}})));
        System.out.println(Arrays.deepToString(c.candyCrush(new int[][]{new int[]{110, 5, 112,
                113, 114}, new
                int[]{210, 211, 5, 213, 214}, new int[]{310, 311, 3, 313, 314}, new int[]{410,
                411, 412, 5, 414}, new int[]{5, 1, 512, 3, 3}, new int[]{610, 4, 1, 613, 614},
                new int[]{710, 1, 2, 713, 714}, new int[]{810, 1, 2, 1, 1}, new int[]{1, 1, 2, 2,
                2}, new int[]{4, 1, 4, 4, 1014}})));
    }
}
