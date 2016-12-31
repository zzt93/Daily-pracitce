package competition.practice.roundE_apac_2017;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.math.DoubleMath;
import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 11/6/16.
 * <p>
 * <h3></h3>
 */
public class B {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/allOneNumber-small-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        long res;
        for (int i = 0; i < trail; i++) {
            res = number2(in.nextLong());
            out.println("Case #" + (i + 1) + ": " + res);
        }
    }

    private static long number(long x) {
        if (x == 2) {
            return 3;
        }
        final double l = Math.sqrt(x);
        for (int j = 2; j < l; j++) {
            if (allOne2(x, j)) {
                return j;
            }
        }
        return x - 1;
    }


    private static long number2(long x) {
        if (x == 2) {
            return 3;
        }
        int l = (int) DoubleMath.log2(x + 1) + 1;
        final double root = Math.sqrt(x);
        for (int i = l; i > 0; i--) {
            int sj = (int) Math.pow(x + 1, 1.0/i);
            int j = sj > 2 ? sj : 2;
            for (; j < root; j++) {
                final double v = (Math.pow(j, i) - 1) / (j - 1);
                if (v > x) {
                    break;
                } else if (v == x){
                    return j;
                }
            }
        }
        return x - 1;
    }

    private static boolean allOne2(long x, int j) {
        long jn = j;
        while ((jn - 1) / (j - 1) < x) {
            jn *= j;
        }
        return 1.0 * (jn - 1) / (j - 1) == x;
    }

    private static Table<Long, Long, Boolean> table = HashBasedTable.create();
    private static boolean allOne(long x, long j) {
        long t = x - 1;
        while (t != 0) {
            if (table.contains(x, j)) {
                return table.get(x, j);
            }
            if (t % j != 0) {
                table.put(x, j, false);
                return false;
            }
            x = t / j;
            t = x - 1;
        }
        table.put(x, j, true);
        return true;
    }

}
