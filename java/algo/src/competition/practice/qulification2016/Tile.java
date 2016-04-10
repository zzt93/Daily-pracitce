package competition.practice.qulification2016;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by zzt on 4/9/16.
 * <p>
 * Usage:
 */
public class Tile {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res1");
        try {
            in = new MyIn("testCase/tile-small-practice.in");//TODO add file name
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        ArrayList<Integer> res;
        for (int i = 0; i < trail; i++) {
            res = findTile(in.nextInt(), in.nextInt(), in.nextInt());
            out.print("Case #" + (i + 1) + ": ");
            if (res.isEmpty()) {
                out.print("IMPOSSIBLE");
            } else {
                for (Integer integer : res) {
                    out.print(integer + " ");
                }
            }
        }
    }

    private static ArrayList<Integer> findTile(int k, int c, int s) {
        ArrayList<Integer> res = new ArrayList<>(s);
        return res;
    }
}
