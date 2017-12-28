package competition.practice.round1A2008.number;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 4/6/15.
 * <p/>
 * Description:
 */
public class Number {
    private static double sqrt5 = Math.sqrt(5);
    /*
    for the expression: x + sqrt(5) * y
    represent last three digits before the decimal point of x, of y, of sqrt(5)*y
    */
    private static int[] base = {3, 1, 2};

    public static int[] number(int n) {
        if (n == 1) {
            return base;
        }
        int sqrt = ((int) Math.sqrt(n));
        int[] sqrtN = number(sqrt);

        // TODO add the remaining part by n+1
        for (int i = sqrt; i < n; i++) {

        }
        return new int[]{};
    }

    private static int[] squreN(int ints[]) {
        double xn = Math.pow(ints[0], 2) + (5 * Math.pow(ints[2], 2));
        double yn = 2 * ints[0] * ints[1];
        // TODO update last part
        double y5n = 0;//+ (2 * ints[0] * ints[1]);
        //        return new int[] {
        //            xn, yn, y5n
        //        };
        return new int[]{};
    }

    private static int mid3(double d) {
        int res = ((int) d);
        return res % 1000;
    }

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/number-small-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        for (int i = 0; i < trail; i++) {
            int res = 0;//TODO invoke some function
            out.println("case #" + (i + 1) + ": " + res);
        }
        //        BigDecimal bigDecimal = new BigDecimal(base);
        //        System.out.println(bigDecimal.pow(9999999));
        //        System.out.println(Math.pow(base, 5));
    }
}
