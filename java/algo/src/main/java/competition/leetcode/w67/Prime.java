package competition.leetcode.w67;

/**
 * Created by zzt on 1/14/18.
 * <p>
 * <h3></h3>
 */
public class Prime {

    boolean[] prime = {false, false, true, true, false, true, false, true, false, false, false,
            true, false, true, false, false, false, true, false, true, false};

    public int countPrimeSetBits(int L, int R) {
        int res = 0;
        for (int i = L; i <= R; i++) {
            if (prime[Integer.bitCount(i)]) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Prime p = new Prime();
        System.out.println(p.countPrimeSetBits(6, 10));
        System.out.println(p.countPrimeSetBits(6, 15));
        System.out.println(p.countPrimeSetBits(600000, 1000000));
    }
}
