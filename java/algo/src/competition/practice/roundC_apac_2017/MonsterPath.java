package competition.practice.roundC_apac_2017;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zzt on 9/30/16.
 * <p>
 * <h3>simulation problem: using recursion; using array to represent state</h3>
 * <h3>math: probability</h3>
 */
public class MonsterPath {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/monster-large-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        in.nextLine();
        double res;
        for (int i = 0; i < trail; i++) {

            int r = in.nextInt();
            int c = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int s = in.nextInt();
            in.nextLine();
            double p = in.nextDouble();
            double q = in.nextDouble();
            in.nextLine();
            double[][] map = new double[r][c];
            double[][] constMap = new double[r][c];
            for (int mapI = 0; mapI < map.length; mapI++) {
                double[] doubles = map[mapI];
                final String[] split = in.nextLine().split(" ");
                for (int j = 0; j < split.length; j++) {
                    String s1 = split[j];
                    if (s1.equals(".")) {
                        doubles[j] = q;
                    } else {
                        doubles[j] = p;
                    }
                }
                constMap[mapI] = Arrays.copyOf(doubles, c);
            }
            res = monster(r, c, x, y, s, constMap, map);

            out.printf("Case #%d: %.7f\n", (i + 1), res);
        }
    }


    private static double monster(int r, int c, int x, int y, int s, double[][] constMap, double[][] map) {
        if (s == 0) {
            return 0;
        }
        List<int[]> possible = getMove(x, y, r, c);
        double max = 0;
        for (int[] ints : possible) {
            final int newx = x + ints[0];
            final int newy = y + ints[1];
            double old = map[newx][newy];
            map[newx][newy] = (1 - constMap[newx][newy]) * old;
            final double tmp = monster(r, c, newx, newy, s - 1, constMap, map) + old;
            if (tmp > max) {
                max = tmp;
            }
            map[newx][newy] = old;
        }
        return max;
    }

    private static final int[] left = {0, -1};
    private static final int[] right = {0, 1};
    private static final int[] up = {-1, 0};
    private static final int[] down = {1, 0};

    private static List<int[]> getMove(int x, int y, int r, int c) {
        List<int[]> res = new ArrayList<>(4);

        if (r > 1) {
            if (x == 0) {
                res.add(down);
            } else if (x == r - 1) {
                res.add(up);
            } else {
                res.add(up);
                res.add(down);
            }
        }
        if (c > 1) {
            if (y == 0) {
                res.add(right);
            } else if (y == c - 1) {
                res.add(left);
            } else {
                res.add(left);
                res.add(right);
            }
        }
        return res;
    }

}
