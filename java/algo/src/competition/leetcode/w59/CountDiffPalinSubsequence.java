package competition.leetcode.w59;

import java.util.Arrays;

/**
 * Created by zzt on 11/19/17.
 * <p>
 * <h3>DP</h3>
 * <li>More dimension</li>
 * <li>Reverse</li>
 * <li>More complex equation</li>
 * <li>for vs recursive</li>
 */
public class CountDiffPalinSubsequence {

    private static final long mod = (long) (1e9 + 7);

    public int countPalindromicSubsequences(String s) {
        int l = s.length();
        int[][] dp = new int[l + 1][l + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        return recur(s.toCharArray(), 0, l, dp);
    }

    int recur(char[] cs, int s, int e, int[][] dp) {
        if (dp[s][e] != -1) return dp[s][e];
        if (e - s <= 2) {
            dp[s][e] = e - s;
            return dp[s][e];
        }

        long cnt = 0;
        int[] L = {-1, -1, -1, -1};
        int[] R = {-1, -1, -1, -1};
        for (int i = s; i < e; i++) {
            if (L[cs[i] - 'a'] == -1) {
                cnt++;
                L[cs[i] - 'a'] = i;
            }
            R[cs[i] - 'a'] = i;
        }
        for (int i = 0; i < 4; i++) {
            if (L[i] != R[i]) {
                cnt++;
                cnt = (cnt + recur(cs, L[i] + 1, R[i], dp)) % mod;
            }
        }
        dp[s][e] = (int) cnt;
        return (int) cnt;
    }

    public static void main(String[] args) {
        CountDiffPalinSubsequence c = new CountDiffPalinSubsequence();
        System.out.println(c.countPalindromicSubsequences("bccb"));
        System.out.println(c.countPalindromicSubsequences("bcbcb"));
    }
}
