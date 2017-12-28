package interview.leetcode._1xx._13x;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zzt on 10/9/17.
 * <p>
 * <h3></h3>
 */
public class PalindromePartition {

    public List<List<String>> partition(String str) {
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
        List<List<String>> res = new ArrayList<>();
        simu(dp, -1, 0, cs, res, new LinkedList<>());
        return res;
    }


    private void simu(boolean[][] dp, int i, int j, char[] cs, List<List<String>> res,
                      LinkedList<String> now) {
        if (i == dp.length - 1 || j == dp.length) {
            res.add(new ArrayList<>(now));
        } else {
            for (int x = j + 1; x < dp[j].length; x++) {
                if (dp[j][x]) {
                    now.add(new String(cs, j, x - j));
                    simu(dp, j, x, cs, res, now);
                    now.removeLast();
                }
            }
        }
    }

    public static void main(String[] args) {
        PalindromePartition partition = new PalindromePartition();
        System.out.println(partition.partition(""));
        System.out.println(partition.partition("a"));
        System.out.println(partition.partition("aaaaa"));
        System.out.println(partition.partition("aabaa"));
        System.out.println(partition.partition("aabba"));
    }
}
