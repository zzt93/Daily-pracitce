package competition.leetcode.week26;

/**
 * Created by zzt on 4/2/17.
 * <p>
 * <h3></h3>
 */
public class LongestUncommonSubsequence1 {


    private static final int MAX = 100;

    private static int[][] dp = new int[MAX + 1][];

    static {
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new int[MAX + 1];
        }
    }

    public int findLUSlength(String a, String b) {
        if (a.length() != b.length()) {
            return Math.max(a.length(), b.length());
        }
        if (a.equals(b)) {
            return -1;
        }
        return a.length();
    }
}
