package interview.leetcode._3xx._31x;


/**
 * Created by zzt on 2/17/18.
 * <p>
 * <h3></h3>
 */
public class SuperUglyNum {

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        int[] index = new int[primes.length];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = min(primes, index, dp);
        }
//        System.out.println(Arrays.toString(dp));
        return dp[n-1];
    }

    private int min(int[] primes, int[] index, int[] dp) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < primes.length; i++) {
            min = Math.min(min, primes[i] * dp[index[i]]);
        }
        for (int i = 0; i < primes.length; i++) {
            if (min == primes[i] * dp[index[i]]) {
                index[i]++;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        SuperUglyNum s = new SuperUglyNum();
        System.out.println(s.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
        System.out.println(s.nthSuperUglyNumber(1200, new int[]{2, 3, 5}));
        System.out.println(s.nthSuperUglyNumber(1, new int[]{2, 3, 5}));
        System.out.println(s.nthSuperUglyNumber(2, new int[]{2, 3, 5}));
    }
}
