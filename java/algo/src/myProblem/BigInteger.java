package myProblem;

import competition.utility.MyOut;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by zzt on 10/13/17.
 * <p>
 * <h3></h3>
 */
public class BigInteger {

    public static void main(String[] args) {
        int bits = 10;
        Random rnd = new Random(47);
        for (int i = 0; i < 8; i++) {
            MyOut out = new MyOut("test_" + (i + 2) + ".in");
            java.math.BigInteger f = new java.math.BigInteger(bits, rnd);
            java.math.BigInteger s = new java.math.BigInteger(bits, rnd);
            out.println(Arrays.toString(f.toString().toCharArray()));
            out.println(Arrays.toString(s.toString().toCharArray()));
            char[] shuffle = s.add(f).toString().toCharArray();
            new MyOut("test_" + (i + 2) + ".out").println(Arrays.toString(shuffle));
            bits += 10;
        }
    }
}
