package interview.google.course;

import java.util.Scanner;

/**
 * Created by zzt on 3/19/18.
 * <p>
 * <h3></h3>
 */
public class MatrixMaxSum {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for (int i = 0; i < t; i++) {
            int r = s.nextInt(), c = s.nextInt();
            int[][] m = new int[r+1][c];
            for (int x = 1; x < r+1; x++) {
                for (int y = 0; y < c; y++) {
                    m[x][y] = s.nextInt() + m[x - 1][y];
                }
            }
            System.out.println(max(m));
        }
    }

    public static int max(int[][] m) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m.length; i++) {
            for (int x = i + 1; x < m.length; x++) {
                max = Math.max(max, dp(m, i, x));
            }
        }
        return max;
    }

    private static int dp(int[][] m, int i, int x) {
        int maxEnd = m[x][0] - m[i][0];
        int max = maxEnd;
        for (int c = 1; c < m[i].length; c++) {
            maxEnd = Math.max(maxEnd + m[x][c] - m[i][c], m[x][c] - m[i][c]);
            max = Math.max(max, maxEnd);
        }
        return max;
    }

}
