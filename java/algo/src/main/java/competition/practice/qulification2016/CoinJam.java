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
public class CoinJam {

    public static final char ONE = '1';
    public static final BigInteger BIG_ONE = new BigInteger("1");
    public static final BigInteger BIG_ZERO = new BigInteger("0");

    private static class Res {
        String str;
        long[] divisors = new long[9];
        private int count = 0;

        public Res(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(str).append(' ');
            for (long divisor : divisors) {
                stringBuilder.append(divisor).append(' ');
            }
            return stringBuilder.toString();
        }

        public void addDivisor(long divisor) {
            divisors[count++] = divisor;
        }
    }

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/coinJam-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        ArrayList<Res> res;
        for (int i = 0; i < trail; i++) {
            res = findNum(in.nextInt(), in.nextInt());
            out.println("Case #" + (i + 1) + ":");
            res.forEach(out::println);
        }
    }

    private static ArrayList<Res> findNum(int n, int j) {

        ArrayList<Res> res = new ArrayList<>(j);
        int max = (int) Math.pow(2, n - 2);
        for (int i = 0; i < max; i++) {
            StringBuilder sb = new StringBuilder(n);
            assert n <= 32;
            String mid = Integer.toBinaryString(0x80000000 | i).substring(32 - (n - 2));
            assert mid.length() == n - 2;
            sb.append(ONE).append(mid).append(ONE);
            //            testAndAddSmall(sb.toString(), res);
            testAndAddLarge(sb.toString(), res);
            if (res.size() == j) {
                break;
            }
        }
        return res;
    }

    private static void testAndAddLarge(String s, ArrayList<Res> res) {
        Res e = new Res(s);
        for (int i = 2; i < 11; i++) {
            BigInteger bigInteger = new BigInteger(s, i);
            long divisor = findDivisor(bigInteger);
            if (divisor == 1) {
                return;
            } else {
                e.addDivisor(divisor);
            }
        }
        res.add(e);
        System.out.println(res.size());
    }


    private static void testAndAddSmall(String sb, ArrayList<Res> res) {
        Res e = new Res(sb);
        for (int i = 2; i < 11; i++) {
            long l = Long.parseLong(sb, i);
            long divisor = findDivisor(l);
            if (divisor == 1) {
                return;
            } else {
                e.addDivisor(divisor);
            }
        }
        res.add(e);
    }


    private static long findDivisor(BigInteger bigInteger) {
        if (bigInteger.and(BigInteger.ONE).equals(BigInteger.ZERO)) {
            return 2;
        }
        if (bigInteger.isProbablePrime(100)) {
            return 1;
        }
        BigInteger bigI = BigInteger.valueOf(3);
        // may be i can be smaller
        for (long i = 3; i < Integer.MAX_VALUE / 10 &&
                bigInteger.compareTo(bigI.pow(2)) >= 0; i += 2) {
            bigI = BigInteger.valueOf(i);
            if (bigInteger.mod(bigI).equals(BigInteger.ZERO)) {
                return i;
            }
        }
        return 1;
    }

    /**
     * @param n n > 1
     *
     * @return smallest divisor
     */
    private static long findDivisor(long n) {
        if ((n & 1) == 0) {
            return 2;
        }
        // only odd factors need to be tested up to n^0.5
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return i;
            }
        }
        return 1;
    }
}
