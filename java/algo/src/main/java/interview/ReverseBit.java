package interview;

import java.util.Random;

/**
 * Created by zzt on 4/26/16.
 * <p>
 * <h3>Problem:</h3>
 * The challenge is to reverse the bits in each byte,
 * given a large array of bytes. What is the fastest possible solution
 * <h3>Solution:</h3>
 * Memorization
 */
public class ReverseBit {
    public static final int BYTE_BITS = 8;
    public static final int BYTE_COUNT = 1 << BYTE_BITS;
    private static byte[] cache = new byte[BYTE_COUNT];

    static {
        for (int i = 0; i < cache.length; i++) {
            cache[i] = reverse(i);
        }
    }

    private static byte reverse(int original) {
        int res = (original >> 4) | ((original & 0x0f) << 4);
        res = ((res & 0xcc) >> 2) | ((res & 0x33) << 2);
        res = ((res & 0xaa) >> 1) | ((res & 0x55) << 1);
        return (byte) res;
    }


    public static byte[] reverseEach(byte[] bytes) {
        byte[] res = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            res[i] = cache[bytes[i] & 0xff];
        }
        return res;
    }

    public static void main(String[] args) {
        Random random = new Random(47);
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        byte[] newBytes = ReverseBit.reverseEach(bytes);
        System.out.printf("%7s : new\n", "old");
        for (int i = 0; i < bytes.length; i++) {
            byte old = bytes[i];
            byte ne = newBytes[i];
            String s1 = Integer.toBinaryString(old | 0x100);
            String s2 = Integer.toBinaryString(ne | 0x100);
            System.out.println(s1.substring(s1.length() - BYTE_BITS, s1.length())
                    + ":" + s2.substring(s2.length() - BYTE_BITS, s2.length()));
        }
    }
}
