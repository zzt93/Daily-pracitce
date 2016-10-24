package competition.practice.roundD_apac_2017;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by zzt on 10/16/16.
 * <p>
 * <h3>Solution</h3>
 * <li>Similarity: produce valid parenthesis, check number when solve smaller problem</li>
 * <li>Path from [0, 0] to [x, y] is the vote order</li>
 *
 */
public class Vote {

    private static Random random = new Random(12);

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/vote-large-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        in.nextLine();
        double res;
        for (int i = 0; i < trail; i++) {

            final int n = in.nextInt();
            final int m = in.nextInt();
            res = vote(n, m, n, m);
            dp.clear();
            out.printf("Case #%d: %.7f\n", (i + 1), res);
        }
    }

    private static HashMap<Integer, HashMap<Integer, Double>> dp = new HashMap<>(2000);

    private static double vote(int x, int y, int n, int m) {
        if (y == 0 && x == 1) {
            return aCome(n, m, 0, 0);
        }
        HashMap<Integer, Double> value;
        if (dp.containsKey(x)) {
            value = dp.get(x);
            if (value.containsKey(y)) {
                return value.get(y);
            }
        } else {
            value = new HashMap<>(2000);
            dp.put(x, value);
        }
        double res = 0;
        if (y > 0) {
            res += vote(x, y - 1, n, m) * bCome(n, m, x, y - 1);
        }
        if (x - 1 > y) {
            res += vote(x - 1, y, n, m) * aCome(n, m, x - 1, y);
        }
        value.put(y, res);
        return res;
    }

    private static double bCome(int n, int m, int x, int y) {
        return 1.0 * (m - y) / (n + m - x - y);
    }

    private static double aCome(int n, int m, int x, int y) {
        return 1.0 * (n - x) / (n + m - x - y);
    }


    //    private static double vote(int n, int m) {
    //        final int all = 1000000;
    //        int count = 0;
    //        for (int i = 0; i < all; i++) {
    //            if (simulate(n, m)) {
    //                count++;
    //            }
    //        }
    //        return (double) count / all;
    //    }
    //
    //    private static boolean simulate(int n, int m) {
    //        final int sum = n + m;
    //        int c1 = 0, c2 = 0;
    //        for (int i = 0; i < sum; i++) {
    //            final int i1 = random.nextInt(sum);
    //            if (i1 < n) {
    //                c1++;
    //            } else {
    //                c2++;
    //            }
    //            if (c1 <= c2) {
    //                return false;
    //            }
    //        }
    //        return true;
    //    }


}
