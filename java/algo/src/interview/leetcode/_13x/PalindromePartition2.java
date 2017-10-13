package interview.leetcode._13x;

/**
 * Created by zzt on 10/10/17.
 * <p>
 * <h3>Brute: all partition, find min length one</h3>
 * <h3>Greedy: failed: xyxyxxyx, aaba</h3>
 * <h3>DP: sub-problem, the dimension -- sub-string</h3>
 */
public class PalindromePartition2 {

    public int minCut(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        char[] cs = str.toCharArray();
        int l = cs.length;
        boolean[][] dp = new boolean[l][l + 1];
        for (int i = 0; i < l; i++) {
            dp[i][i] = dp[i][i + 1] = true;
        }
        for (int s = l - 1; s >= 0; s--) {
            for (int e = s + 2; e <= l; e++) {
                dp[s][e] = dp[s + 1][e - 1] && (cs[s] == cs[e - 1]);
            }
        }

        int[] min = new int[l];
        min[0] = 0;
        for (int i = 1; i < cs.length; i++) {
            if (dp[0][i + 1]) {
                min[i] = 0;
                continue;
            }
            int tem = min[i - 1] + 1;
            for (int x = 1; x < i; x++) {
                if (cs[x] == cs[i] && dp[x][i + 1]) {
                    int cut = min[x - 1] + 1;
                    if (cut < tem) {
                        tem = cut;
                    }
                }
            }
            min[i] = tem;
        }
        return min[l - 1];
    }

    public static void main(String[] args) {
        PalindromePartition2 partition2 = new PalindromePartition2();
        System.out.println(partition2.minCut("leet"));
        System.out.println(partition2.minCut("aab"));
        System.out.println(partition2.minCut(""));
        System.out.println(partition2.minCut("a"));
        System.out.println(partition2.minCut("aaaaa"));
        System.out.println(partition2.minCut("aaaba"));
        System.out.println(partition2.minCut("aabba"));
        System.out.println(partition2.minCut("abbba"));
        System.out.println(partition2.minCut("bbba"));
        System.out.println(partition2.minCut("xyxyxxyx"));
        System.out.println(partition2.minCut("aaabaa"));
    }
}
