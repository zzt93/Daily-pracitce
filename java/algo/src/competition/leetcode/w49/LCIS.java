package competition.leetcode.w49;

/**
 * Created by zzt on 9/10/17.
 * <p>
 * <h3></h3>
 */
public class LCIS {

    public int findLengthOfLCIS(int[] n) {
        if (n.length == 0) {
            return 0;
        }
        int[] dp = new int[n.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < n.length; i++) {
            if (n[i] > n[i-1]) {
                dp[i] = dp[i-1] + 1;
                if (dp[i] > max) {
                    max = dp[i];
                }
            } else {
                dp[i] = 1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LCIS lcis = new LCIS();
        System.out.println(lcis.findLengthOfLCIS(new int[]{1,3,5,4,7}));
        System.out.println(lcis.findLengthOfLCIS(new int[]{}));
        System.out.println(lcis.findLengthOfLCIS(new int[]{2,2,2,2,3}));
        System.out.println(lcis.findLengthOfLCIS(new int[]{2,2,2,2}));
    }
}
