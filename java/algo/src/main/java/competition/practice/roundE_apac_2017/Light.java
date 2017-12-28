package competition.practice.roundE_apac_2017;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 11/6/16.
 * <p>
 * <h3></h3>
 */
public class Light {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/A-large-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        long res;
        for (int i = 0; i < trail; i++) {
            in.nextLine();
            res = count(in.nextLine(), in.nextLong(), in.nextLong());
            out.println("Case #" + (i + 1) + ": " + res);
        }
    }

    private static long count(String s, long i, long j) {
        i--;
        j--;

        final char[] chars = s.toCharArray();
        int c = 0, more = 0;
        final int pl = chars.length;
        long roundI = round(i, pl, true);
        long roundJ = round(j, pl, false);
        assert roundI % pl == 0;
        assert roundJ % pl == pl - 1;

        final long jl = j % pl;
        final long il = i % pl;
        for (int x = 0; x < pl; x++) {
            if (chars[x] == 'B') {
                c++;
                if (x >= 0 && x <= jl) {
                    more++;
                }
                if (x >= il && x < pl) {
                    more++;
                }
            }
        }
        long times = (roundJ - roundI + 1) / pl;
        return times * c + more;
    }

    private static long round(long i, int pl, boolean up) {
        long res = i;
        final long r = i % pl;
        if (up) {
            res = res + (pl - r);
        } else {
            res = res - (r + 1);
        }
        return res;
    }
}
