package competition.practice.round2016;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 9/29/15.
 * <p>
 * Description:
 * <a href="https://code.google.com/codejam/contest/dashboard?c=6234486#s=p1">Captain</a>
 */
public class Captain {
    public static final double G = 9.8;

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/captain-small-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        double res;
        for (int i = 0; i < trail; i++) {
            res = CalDegree(in.nextInt(), in.nextInt());
            out.println("Case #" + (i + 1) + ": " + String.format("%.7f", res));
        }
    }

    private static double CalDegree(int v, int dest) {
        double sin2d = dest * G / v / v;
        if (sin2d > 1) {
            sin2d = 1;
        }
        return Math.toDegrees(Math.asin(sin2d)) / 2;
    }
}
