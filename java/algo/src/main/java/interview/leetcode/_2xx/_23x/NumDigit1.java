package interview.leetcode._2xx._23x;

/**
 * Created by zzt on 12/27/17.
 * <p>
 * <h3></h3>
 */
public class NumDigit1 {

    public int countDigitOne(int n) {
        if (n < 1) return 0;
        int len = getLen(n);
        int[] ni = new int[len + 1];
        for (int i = 0; i < len; i++) {
            ni[i + 1] = n % 10;
            n /= 10;
        }
        return count(len, ni, true, 0);
    }

    private int count(int x, int[] ni, boolean limit, int oneC) {
        if (x == 1) {
            if (limit) {
                return ni[1] * oneC + oneC + (ni[1] >= 1 ? 1 : 0);
            } else {
                return 9 * oneC + oneC + 1;
            }
        }
        if (limit) {
            int res = 0;
            if (ni[x] > 1) {
                res += count(x - 1, ni, false, oneC + 1);
                res += count(x - 1, ni, false, oneC) * (ni[x] - 1);
                res += count(x - 1, ni, true, oneC);
            } else if (ni[x] == 1) {
                res += count(x - 1, ni, false, oneC);
                res += count(x - 1, ni, true, oneC + 1);
            } else {
                res += count(x - 1, ni, true, oneC);
            }
            return res;
        } else {
            //            if (oneC > 0) return (int) Math.pow(10, x) * oneC +;
            //            else {
            return count(x - 1, ni, false, oneC + 1)
                    + 9 * count(x - 1, ni, false, oneC);
            //            }
        }
    }

    private int getLen(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        NumDigit1 n = new NumDigit1();
        System.out.println(n.countDigitOne(Integer.MAX_VALUE));
        System.out.println(n.countDigitOne(-1));
        System.out.println(n.countDigitOne(0));
        System.out.println(n.countDigitOne(10));
        System.out.println(n.countDigitOne(11));
        System.out.println(n.countDigitOne(13));
        System.out.println(n.countDigitOne(1234));
        System.out.println(n.countDigitOne(5234));
        System.out.println(n.countDigitOne(123456789));
    }
}
