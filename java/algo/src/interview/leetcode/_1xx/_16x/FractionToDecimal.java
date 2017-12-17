package interview.leetcode._1xx._16x;

import java.util.HashMap;

/**
 * Created by zzt on 11/14/17.
 * <p>
 * <h3></h3>
 */
public class FractionToDecimal {

    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        if (numerator == 0) {
            return "0";
        }
        if (numerator < 0 != denominator < 0) {
            sb.append("-");
        }
        long nabs = Math.abs((long) numerator);
        long abs = Math.abs((long) denominator);
        sb.append(nabs / abs);
        long re = nabs % abs;
        HashMap<Long, Integer> map = new HashMap<>();
        boolean first = true;
        int i = sb.length();
        while (re != 0 && !map.containsKey(re)) {
            if (first) {
                sb.append(".");
                i++;
                first = false;
            }
            long d = re * 10 / abs;
            map.put(re, i++);
            re = re * 10 % abs;
            sb.append(d);
        }
        if (re == 0) {
            return sb.toString();
        }
        return sb.insert(map.get(re), "(").append(')').toString();
    }

    public static void main(String[] args) {
        FractionToDecimal f = new FractionToDecimal();
        System.out.println(f.fractionToDecimal(-1, 3));
        System.out.println(f.fractionToDecimal(-1, -2147483648));
        System.out.println(f.fractionToDecimal(-2147483648, -3));
        System.out.println(f.fractionToDecimal(-50, 3));
        System.out.println(f.fractionToDecimal(-50, 8));
        System.out.println(f.fractionToDecimal(50, -8));
        System.out.println(f.fractionToDecimal(1, 3));
        System.out.println(f.fractionToDecimal(1, 7));
        System.out.println(f.fractionToDecimal(-1, 7));
        System.out.println(f.fractionToDecimal(1, 2));
        System.out.println(f.fractionToDecimal(10, 2));
        System.out.println(f.fractionToDecimal(10, 3));
        System.out.println(f.fractionToDecimal(234, 123));
    }
}
