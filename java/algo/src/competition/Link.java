package competition;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 6/17/16.
 * <p>
 * <h3></h3>
 */
public class Link {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/link-small-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        int res;
        for (int i = 0; i < trail; i++) {
            final int row = in.nextInt();
            final int col = in.nextInt();
            int[][] table = new int[row][];
            for (int r = 0; r < row; r++) {
                table[row] = new int[col];
                for (int c = 0; c < col; c++) {
                    table[row][col] = in.nextInt();
                }
            }
            res = link(row, col, table, in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
            out.println("Case #" + (i + 1) + ": " + res);
        }
    }

    private static int link(int row, int col, int[][] table, int r1, int c1, int r2, int c2) {
        int[][] realTable = new int[row + 2][col + 2];
        return 0;
    }
}
