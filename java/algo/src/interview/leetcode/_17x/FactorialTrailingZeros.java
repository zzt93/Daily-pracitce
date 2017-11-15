package interview.leetcode._17x;

/**
 * Created by zzt on 11/15/17.
 * <p>
 * <h3></h3>
 */
public class FactorialTrailingZeros {

    public int trailingZeroes(int n) {
        long e = 5;
        int s = 0;
        while (n / e > 0) {
            s += n / e;
            e *= 5;
        }
        return s;
    }

    public static void main(String[] args) {
        FactorialTrailingZeros f = new FactorialTrailingZeros();
        System.out.println(f.trailingZeroes(7));
        System.out.println(f.trailingZeroes(17));
        System.out.println(f.trailingZeroes(25));
        System.out.println(f.trailingZeroes(27));
        System.out.println(f.trailingZeroes(2237));
        System.out.println(f.trailingZeroes(132349));
        System.out.println(f.trailingZeroes(1808548329));
    }
}
