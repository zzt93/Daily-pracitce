package competition.practice.qulification2016;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.TreeSet;

/**
 * Created by zzt on 4/9/16.
 * <p>
 * Usage:
 */
public class CountingSheep {

    public static long countBits(int n) {
        TreeSet<Integer> counts = new TreeSet<>();
        long tmp = 0;
        for (int i = 1; i <= 100; i++) {
            tmp = i * n;
            while (tmp > 0) {
                counts.add((int) (tmp % 10));
                tmp /= 10;
            }
            if (counts.size() == 10) {
                return i * n;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/count-large.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        long res;
        for (int i = 0; i < trail; i++) {
            res = countBits(in.nextInt());
            if (res < 0) {
                out.println("Case #" + (i + 1) + ": " + "INSOMNIA");
            } else {
                out.println("Case #" + (i + 1) + ": " + res);
            }
        }
    }
}
