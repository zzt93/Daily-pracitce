package interview.leetcode._20x;

/**
 * Created by zzt on 12/6/17.
 * <p>
 * <h3></h3>
 */
public class CountPrimes {

    public int countPrimes(int n) {
        boolean[] prime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!prime[i]) {
                count++;
                for (int j = i; i * j < n && i * j > j; j++) {
                    prime[i * j] = true;
                }
            }
        }
        return count;
    }
}
