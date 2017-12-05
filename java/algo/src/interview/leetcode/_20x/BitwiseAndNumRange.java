package interview.leetcode._20x;

/**
 * Created by zzt on 12/5/17.
 * <p>
 * <h3></h3>
 */
public class BitwiseAndNumRange {

    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0) {
            return 0;
        }
        long pow = 1;
        while (m >= pow) {
            pow *= 2;
        }
        if (n < pow) {
            pow /= 2;
            return (int) (pow + rangeBitwiseAnd(getSub(m, pow), getSub(n, pow)));
        } else {
            return 0;
        }
    }

    private int getSub(int m, long pow) {
        return (int) (m & (~pow));
    }

    public static void main(String[] args) {
        BitwiseAndNumRange b = new BitwiseAndNumRange();
        System.out.println(b.rangeBitwiseAnd(2147483646, 2147483647));
        System.out.println(b.rangeBitwiseAnd(6, 7));
        System.out.println(b.rangeBitwiseAnd(5, 7));
        System.out.println(b.rangeBitwiseAnd(4, 7));
        System.out.println(b.rangeBitwiseAnd(0, 1));
        System.out.println(b.rangeBitwiseAnd(1, 1));
        System.out.println(b.rangeBitwiseAnd(11, 11));
    }
}
