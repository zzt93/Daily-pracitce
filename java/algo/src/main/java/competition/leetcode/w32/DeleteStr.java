package competition.leetcode.w32;

/**
 * Created by zzt on 5/14/17.
 * <p>
 * <h3></h3>
 */
public class DeleteStr {


    public int minDistance(String word1, String word2) {
        return word1.length() + word2.length() - lcs(word1.toCharArray(), word2.toCharArray()) * 2;
    }

    private int lcs(char[] s1, char[] s2) {
        if (s1.length == 0 || s2.length == 0) {
            return 0;
        }
        int m = s1.length, n = s2.length;
        int[][] dp = new int[m][n];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int i = 1; i < m; i++) {
            dp[i][0] = s1[i] == s2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = s2[i] == s1[0] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int x = 1; x < n; x++) {
                int max = Math.max(dp[i - 1][x], dp[i][x - 1]);
                dp[i][x] = s1[i] == s2[x] ? dp[i - 1][x - 1] + 1 : max;
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        DeleteStr deleteStr = new DeleteStr();
        //        System.out.println(deleteStr.minDistance("abc", "aec"));
        //        System.out.println(deleteStr.minDistance("sea", "eat"));
        //        System.out.println(deleteStr.minDistance("sea", "eat"));
        //        System.out.println(deleteStr.minDistance("agcat", "gac"));
        System.out.println(deleteStr.minDistance("xmjyauz", "mzjawxu"));
    }
}
