package interview.leetcode._4xx._47x;

public class OnesAndZeros_474 {

    public int findMaxForm(String[] ss, int m, int n) {
        if (ss.length == 0) return 0;
        int[][][] dp = new int[ss.length][m+1][n+1];
        int[] oz = oz(ss[0]);
        for (int i = oz[1]; i < m+1; i++) {
            for (int j = oz[0]; j < n+1; j++) {
                dp[0][i][j] = 1;
            }
        }
        for (int i = 1; i < ss.length; i++) {
            oz = oz(ss[i]);
            for (int j = 0; j < m+1; j++) {
                for (int k = 0; k < n+1; k++) {
                    if (j < oz[1] || k < oz[0]) dp[i][j][k] = dp[i-1][j][k];
                    else dp[i][j][k] = Math.max(dp[i-1][j-oz[1]][k-oz[0]]+1,
                            dp[i-1][j][k]);
                }
            }
        }
        return dp[ss.length-1][m][n];
    }

    private int[] oz(String s) {
        int o = 0, z = 0;
        for (char c: s.toCharArray()) {
            if (c == '1') o++;
            else z++;
        }
        return new int[]{o, z};
    }
}
