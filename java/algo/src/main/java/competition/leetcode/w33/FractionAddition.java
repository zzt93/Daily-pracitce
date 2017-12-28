package competition.leetcode.w33;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 5/21/17.
 * <p>
 * <h3></h3>
 */
public class FractionAddition {

    private static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    private static class Num {
        private int numerator;
        private int denominator;

        public Num(int numerator) {
            this.numerator = numerator;
        }

        public void add(Num o) {
            long lcm = lcm(denominator, o.denominator);
            long m1 = lcm / denominator;
            long m2 = lcm / o.denominator;
            numerator = (int) (numerator * m1 + m2 * o.numerator);
            denominator *= m1;
            long gcd = gcd(Math.abs(numerator), denominator);
            numerator /= gcd;
            denominator /= gcd;
        }

        @Override
        public String toString() {
            return "" + numerator + "/" + denominator;
        }
    }

    public String fractionAddition(String expression) {
        List<Num> numList = prepare(expression);
        for (int i = 1; i < numList.size(); i++) {
            numList.get(0).add(numList.get(i));
        }
        return numList.get(0).toString();
    }

    private List<Num> prepare(String expression) {
        char[] chars = expression.toCharArray();
        List<Num> res = new ArrayList<>();
        int last = 0;
        Num num = null;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == '/') {
                num = new Num(Integer.parseInt(expression.substring(last, i)));
                last = i;
            } else if (aChar == '-' || aChar == '+') {
                if (num != null) {
                    num.denominator = Integer.parseInt(expression.substring(last + 1, i));
                    res.add(num);
                }
                last = i;
            }
        }
        num.denominator = Integer.parseInt(expression.substring(last + 1));
        res.add(num);
        return res;
    }

    public static void main(String[] args) {
        FractionAddition fractionAddition = new FractionAddition();
        System.out.println(fractionAddition.fractionAddition("1/3-1/2"));
        System.out.println(fractionAddition.fractionAddition("-1/2+1/2+1/3"));
        System.out.println(fractionAddition.fractionAddition("-1/2-1/2"));
        System.out.println(fractionAddition.fractionAddition("-1/2+1/2"));
        System.out.println(fractionAddition.fractionAddition("5/3+1/3"));
        System.out.println(fractionAddition.fractionAddition("5/4+1/6"));
    }
}
