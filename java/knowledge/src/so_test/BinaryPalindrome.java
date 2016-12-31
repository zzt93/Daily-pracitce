package so_test;

import java.util.Arrays;

/**
 * Created by zzt on 12/31/16.
 * <p>
 * <h3></h3>
 */
public class BinaryPalindrome {

    public static final int LONG_BITS = 64;
    /**
     * except the surrounding '1'
     * e.g. '1' '00' '1'
     */
    private static final int MAX_MID_LEN = LONG_BITS - 2;
    private static final long[] indexRange = new long[MAX_MID_LEN];

    static {
        indexRange[0] = 0;
        for (int i = 1; i < indexRange.length; i++) {
            indexRange[i] = (long) (indexRange[i - 1] + Math.pow(2, Math.ceil((i - 1) * 1.0 / 2)));
            if (indexRange[i] == Long.MAX_VALUE) {
                System.out.println("Out of range at: " + i);
                break;
            }
        }
    }

    /**
     * @param n count from 0
     *
     * @return nth binary palindrome number
     */
    public static long magical(long n) {
        if (n == 0 || n == 1) {
            return n;
        }
        long N = n - 2;
        return Long.parseLong(concat(N), 2);
    }

    private static String concat(long N) {
        int midLen = Arrays.binarySearch(indexRange, N);
        if (midLen < 0) {
            midLen = -midLen - 2;
        }
        long remaining = N - indexRange[midLen];
        if (remaining > indexRange[midLen]) {
            throw new IllegalArgumentException("too large N: " + (N + 2));
        }
        String mid = mirror(remaining, midLen);
        return '1' + mid + '1';
    }

    private static String mirror(long n, int midLen) {
        int halfLen = (int) Math.ceil(midLen * 1.0 / 2);
        // produce fixed length binary string
        final String half = Long.toBinaryString(n | (1 << halfLen)).substring(1);
        if (midLen % 2 == 0) {
            return half + new StringBuilder(half).reverse().toString();
        } else {
            return half + new StringBuilder(half).reverse().toString().substring(1);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Long.toBinaryString(magical(i)));
        }
        //        System.out.println(Long.toBinaryString(magical(Long.MAX_VALUE)));
    }
}
