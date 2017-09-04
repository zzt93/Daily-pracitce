package interview.leetcode._9x;

/**
 * Created by zzt on 8/8/17.
 * <p>
 * <h3>Similarity: LCSe</h3>
 * <h3>DP: sub-problem: can s1[0,x] & s2[0,y] compose s3[0, x+y]?</h3>
 * <p>
 * Simulation will be 2**n in some corner case, which can be seen as
 * the enumeration of all paths from DP's matrix[0]0] to matrix[m][n].
 * <p>
 * In other word, two dimensional DP matrix == 2**n simulation.
 * </p>
 */
public class InterleavingStr {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        int m = c1.length + 1;
        int n = c2.length + 1;
        boolean[][] dp = new boolean[m][n];

        dp[0][0] = true;
        for (int i = 0; i < m - 1; i++) {
            dp[i + 1][0] = c1[i] == c3[i] && dp[i][0];
        }
        for (int i = 0; i < n - 1; i++) {
            dp[0][i + 1] = c2[i] == c3[i] && dp[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int x = 1; x < n; x++) {
                dp[i][x] = (c3[i + x - 1] == c1[i - 1] && dp[i - 1][x])
                        || (c3[i + x - 1] == c2[x - 1] && dp[i][x - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }


    public static void main(String[] args) {
        InterleavingStr str = new InterleavingStr();
        System.out.println(str.isInterleave("db", "b", "cbb"));
        System.out.println(str.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(str.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(str.isInterleave("aaaaa", "aaaaa", "aaaaaaaaaa"));
        System.out.println(str.isInterleave("aaaaa", "aaaaa", "aaaaaaaaab"));
        System.out.println(str.isInterleave("ac", "ac", "acac"));
        System.out.println(str.isInterleave("ac", "ac", "acca"));
        System.out.println(str.isInterleave("ac", "ac", "aacc"));
        System.out.println(str.isInterleave("ac", "acd", "aacdc"));
        System.out.println(str.isInterleave("ac", "acd", "aadcc"));
    }
}
