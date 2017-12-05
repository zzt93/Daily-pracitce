package interview.leetcode._19x;

/**
 * Created by zzt on 12/4/17.
 * <p>
 * <h3></h3>
 */
public class NumOf1Bits {

    public int hammingWeight(int n) {
        int c = 0;
        while (n != 0) {
            n = n & (n - 1);
            c++;
        }
        return c;
    }

    public static void main(String[] args) {
        NumOf1Bits n = new NumOf1Bits();
        System.out.println(n.hammingWeight(1));
        System.out.println(n.hammingWeight(2));
        System.out.println(n.hammingWeight(3));
        System.out.println(n.hammingWeight(4));
        System.out.println(n.hammingWeight(-1));
        System.out.println(n.hammingWeight(11));
    }
}
