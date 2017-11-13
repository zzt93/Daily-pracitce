package interview.leetcode._16x;

import java.util.Arrays;

/**
 * Created by zzt on 11/13/17.
 * <p>
 * <h3></h3>
 */
public class MaxGap {

    public int maximumGap(int[] n) {
        if (n.length < 2) {
            return 0;
        }
        return radix(n);
    }

    private int radix(int[] n) {
        int bits = 10;
        int[] tmp = new int[n.length];
        int exp = 1;
        int m = Arrays.stream(n).max().getAsInt();
        for (int i = 0; m / exp > 0; i++) {
            int[] bucket = new int[bits];
            for (int x : n) {
                bucket[(x / exp) % 10]++;
            }
            for (int y = 1; y < bucket.length; y++) {
                bucket[y] += bucket[y - 1];
            }
            for (int y = n.length - 1; y >= 0; y--) {
                int x = n[y];
                tmp[--bucket[(x / exp) % 10]] = x;
            }
            System.arraycopy(tmp, 0, n, 0, n.length);
            exp *= 10;
        }
        int max = 0;
        for (int i = 1; i < n.length; i++) {
            max = Math.max(max, n[i] - n[i - 1]);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxGap m = new MaxGap();
        System.out.println(m.maximumGap(new int[]{1, 2, 3, 4}));
        System.out.println(m.maximumGap(new int[]{4, 3, 2, 1}));
        System.out.println(m.maximumGap(new int[]{6, 5, 3, 1, 8, 7}));
        System.out.println(m.maximumGap(new int[]{2146, 2145, 2143, 2141, 2148, 2147}));
    }
}
