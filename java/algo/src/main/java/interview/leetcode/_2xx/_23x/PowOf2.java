package interview.leetcode._2xx._23x;

/**
 * Created by zzt on 12/27/17.
 * <p>
 * <h3></h3>
 */
public class PowOf2 {

    public boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }
        return n == 1 || n % 2 == 0 && isPowerOfTwo(n / 2);
    }

    public static void main(String[] args) {
        PowOf2 p = new PowOf2();
        System.out.println(p.isPowerOfTwo(-2));
        System.out.println(p.isPowerOfTwo(0));
        System.out.println(p.isPowerOfTwo(1));
        System.out.println(p.isPowerOfTwo(-1));
    }
}
