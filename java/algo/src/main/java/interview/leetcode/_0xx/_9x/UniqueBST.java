package interview.leetcode._0xx._9x;

/**
 * Created by zzt on 8/5/17.
 * <p>
 * <h3></h3>
 */
public class UniqueBST {


    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = sum(dp, i);
        }
        return dp[n];
    }

    private int sum(int[] dp, int i) {
        int res = 0;
        for (int x = 0; x < i; x++) {
            res += (dp[x] * dp[i - x - 1]);
        }
        return res;
    }

    public static void main(String[] args) {
        UniqueBST bst = new UniqueBST();
        System.out.println(bst.numTrees(3));
        System.out.println(bst.numTrees(10));
    }
}
