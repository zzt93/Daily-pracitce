package competition.practice.roundC_apac_2017;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by zzt on 10/1/16.
 * <p>
 * <h3>DP: find the smaller size of problem && using extra memory to save time</h3>
 * <h3>good solution is always simple and not so much special case</h3>
 * <h3>long is slower, but without worry of overflow</h3>
 */
public class SafeSquares {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/safe-squares-large-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();

        long res;
        for (int i = 0; i < trail; i++) {

            int r = in.nextInt();
            int c = in.nextInt();
            int k = in.nextInt();
            in.nextLine();
            //            int[][] map = new int[r][];
            TreeSet<Integer>[] rowMap = new TreeSet[r];
            for (int jj = 0; jj < rowMap.length; jj++) {
                rowMap[jj] = new TreeSet<>();
            }
            TreeSet<Integer>[] colMap = new TreeSet[c];
            for (int jj = 0; jj < colMap.length; jj++) {
                colMap[jj] = new TreeSet<>();
            }
            for (int jj = 0; jj < k; jj++) {
                int x = in.nextInt();
                int y = in.nextInt();
                rowMap[x].add(y);
                colMap[y].add(x);
            }
            res = safe(r, c, rowMap, colMap);

            out.println("Case #" + (i + 1) + ": " + res);
        }
    }

    private static long safe(int r, int c, TreeSet<Integer>[] rowMap, TreeSet<Integer>[] colMap) {
        int[][] dp = new int[r][];
        final int[] ints = new int[c];
        Arrays.fill(ints, -1);
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Arrays.copyOf(ints, c);
        }
        long sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sum += findMax(i, j, r, c, rowMap, colMap, dp);
            }
        }

        return sum;
    }

    private static int findMax(int i, int j, int r, int c, TreeSet<Integer>[] rowMap, TreeSet<Integer>[] colMap, int[][] dp) {
        if (i == r || j == c) {
            return 0;
        }
        if (dp[i][j] >= 0) {
            return dp[i][j];
        }
        // find col max
        final Integer colCeil = rowMap[i].ceiling(j);
        int cGap;
        if (colCeil == null) {
            cGap = c - j;
        } else if (colCeil == j) {
            return 0;
        } else {
            cGap = colCeil - j;
        }

        final Integer rowCeil = colMap[j].ceiling(i);
        int rGap;
        if (rowCeil == null) {
            rGap = r - i;
        } else if (rowCeil == i) {
            return 0;
        } else {
            rGap = rowCeil - i;
        }

        int maxPossible = Math.min(cGap, rGap);
        final int max = findMax(i + 1, j + 1, r, c, rowMap, colMap, dp);
        if (max >= maxPossible - 1) {
            dp[i][j] = maxPossible;
            return maxPossible;
        }
        dp[i][j] = max + 1;
        return max + 1;
    }

}
