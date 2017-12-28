package competition.practice.qu2017;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 4/8/17.
 * <p>
 * <h3></h3>
 */
public class TidyNum {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/tidy-large-practice.in");//TODO add file name
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        long res;
        for (int i = 0; i < trail; i++) {
            res = tidy(in.nextLong());
            out.println("Case #" + (i + 1) + ": " + res);
            //            out.printf("Case #%d: %.7f\n", (i + 1), res);
        }
    }

    private static long tidy(long i) {
        char[] s = Long.toString(i).toCharArray();
        boolean tidy = true;
        for (int x = s.length - 1; x >= 1; x--) {
            if (s[x] < s[x - 1]) {
                tidy = false;
                s[x - 1]--;
                for (int y = x; y < s.length; y++) {
                    s[y] = '9';
                }
            }
        }
        if (tidy) {
            return i;
        }
        return Long.parseLong(new String(s));
    }

}
