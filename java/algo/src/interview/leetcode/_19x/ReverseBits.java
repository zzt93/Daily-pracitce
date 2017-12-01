package interview.leetcode._19x;

/**
 * Created by zzt on 12/1/17.
 * <p>
 * <h3></h3>
 */
public class ReverseBits {

    public int reverseBits(int n) {
        long t = n;
        t = (t >> 16) | (t << 16);
        t = ((t & 0x00ff00ff) << 8) | ((t & 0xff00ff00) >> 8);
        t = ((t & 0x0f0f0f0f) << 4) | ((t & 0xf0f0f0f0) >> 4);
        t = ((t & 0x33333333) << 2) | ((t & 0xcccccccc) >> 2);
        return (int) (((t & 0x55555555) << 1) | ((t & 0xaaaaaaaa) >> 1));
    }

    public static void main(String[] args) {
        ReverseBits r = new ReverseBits();
        System.out.println(r.reverseBits(2));
    }
}
