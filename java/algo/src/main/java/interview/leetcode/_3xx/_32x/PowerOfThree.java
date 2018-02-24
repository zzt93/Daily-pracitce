package interview.leetcode._3xx._32x;

/**
 * Created by zzt on 2/21/18.
 * <p>
 * <h3></h3>
 */
public class PowerOfThree {

    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    public boolean isPowerOfFour(int n) {
        return n > 0 && ((n & (n-1)) == 0) && ((n-1)%3==0);
    }
}
