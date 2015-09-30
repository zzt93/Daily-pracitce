package practice.round2016;

import utility.MyIn;
import utility.MyOut;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 9/29/15.
 * <p>
 * Description:
 */
public class Captain {
    public static final double G = 9.8;

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/captain-small-practice.in");//TODO add file name
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        double res;
        for (int i = 0; i < trail; i++) {
            res = CalDegree(in.nextInt(), in.nextInt());
            out.println("Case #" + (i + 1) + ": " + res);
        }
    }

    private static double CalDegree(int dest, int v) {
        double sin2d = dest * G / v / v;
        return Math.asin(sin2d) / 2;
    }
}
